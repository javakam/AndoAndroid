### onSaveInstanceState源码
---
>Activity失去焦点，很可能被进程终止！，这时候就需要能保存当前的状态。
后台的activity被系统自动回收的话，再次回到界面的时候恢复数据。

- DEMO : com.ishiqing.base.BaseActivity
- 源码 :
```
/**
 * 两个核心注释 1执行机制；另一个说的是执行时机和Activity生命周期的关系
 * 1 在一个Activity将要被kill之前，保存实力状态（执行此方法），以便在onCreate和onRestoreInstanceState中恢复状态。
 * Called to retrieve per-instance state from an activity before being killed
 * so that the state can be restored in {@link #onCreate} or {@link #onRestoreInstanceState}
 * (the {@link Bundle} populated by this method will be passed to both).
 *
 * 2 执行时机：onStop之前，和onPause的先后顺序不确定
 * <p>If called, this method will occur before {@link #onStop}.  There are
 * no guarantees about whether it will occur before or after {@link #onPause}.
 *
 * @param outState Bundle in which to place your saved state.
 *
 * @see #onCreate
 * @see #onRestoreInstanceState
 * @see #onPause
 */
protected void onSaveInstanceState(Bundle outState) {
    //1) outState放了一个tag调用了mWindow里面的 saveHierarchyState 方法，继续分析Window源代码。
    /*
    调用顺序 -> PhoneWindow.saveHierarchyState -> View.saveHierarchyState -> View.dispatchSaveInstanceState
    mWindow.saveHierarchyState()  -- 源码如下
    */
    outState.putBundle(WINDOW_HIERARCHY_TAG, mWindow.saveHierarchyState());
    outState.putInt(LAST_AUTOFILL_ID, mLastAutofillId);
    Parcelable p = mFragments.saveAllState();
    if (p != null) {
        outState.putParcelable(FRAGMENTS_TAG, p);
    }
    if (mAutoFillResetNeeded) {
        outState.putBoolean(AUTOFILL_RESET_NEEDED, true);
        getAutofillManager().onSaveInstanceState(outState);
    }
    getApplication().dispatchActivitySaveInstanceState(this, outState);
}
```
2) window是抽象类调用子类PhoneWindow里面的saveHierarchyState方法代码如下：
```
@Override
public Bundle saveHierarchyState() {
    // 初始化 Bundle 对象
    Bundle outState = new Bundle();
    if (mContentParent == null) {
        return outState;
    }
    // new SparseArray()并且把自己放到 outState 当中
    SparseArray<Parcelable> states = new SparseArray<Parcelable>();
    // 整个 View 树的顶层视图保存了层级状态 -- 源码如下
    mContentParent.saveHierarchyState(states);
    outState.putSparseParcelableArray(VIEWS_TAG, states);

    // Save the focused view ID.
    final View focusedView = mContentParent.findFocus();
    if (focusedView != null && focusedView.getId() != View.NO_ID) {
        outState.putInt(FOCUSED_ID_TAG, focusedView.getId());
    }

    // save the panels
    SparseArray<Parcelable> panelStates = new SparseArray<Parcelable>();
    savePanelState(panelStates);
    if (panelStates.size() > 0) {
        outState.putSparseParcelableArray(PANELS_TAG, panelStates);
    }
    if (mDecorContentParent != null) {
        SparseArray<Parcelable> actionBarStates = new SparseArray<Parcelable>();
        mDecorContentParent.saveToolbarHierarchyState(actionBarStates);
        outState.putSparseParcelableArray(ACTION_BAR_TAG, actionBarStates);
    }
    return outState;
}
```
View.dispatchSaveInstanceState -> eg:com.ishiqing.fragment.MainController
```
/**
 * Called by {@link #saveHierarchyState(android.util.SparseArray)} to store the state for
 * this view and its children. May be overridden to modify how freezing happens to a
 * view's children; for example, some views may want to not store state for their children.
 *
 * @param container The SparseArray in which to save the view's state.
 *
 * @see #dispatchRestoreInstanceState(android.util.SparseArray)
 * @see #saveHierarchyState(android.util.SparseArray)
 * @see #onSaveInstanceState()
 */
protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
    if (mID != NO_ID && (mViewFlags & SAVE_DISABLED_MASK) == 0) {
        mPrivateFlags &= ~PFLAG_SAVE_STATE_CALLED;
        Parcelable state = onSaveInstanceState();
        if ((mPrivateFlags & PFLAG_SAVE_STATE_CALLED) == 0) {
            throw new IllegalStateException(
                    "Derived class did not call super.onSaveInstanceState()");
        }
        if (state != null) {
            // Log.i("View", "Freezing #" + Integer.toHexString(mID) + ": " + state);
            container.put(mID, state);
        }
    }
}
```
可以看出，①mID != NO_ID 判断一个View必须有一个id，也就是说你要么在xml里通过android:id指定，要么在代码里调用setId，
我们搜索 NO_ID 看到代码如下：
```
/**
 * Used to mark a View that has no ID.
 */
public static final int NO_ID = -1;
```
原来 NO_ID 用来标记没有id的View，搜索mID可知原来在如下代码赋值
```
public void setId(@IdRes int id) {
    mID = id;
    if (mID == View.NO_ID && mLabelForId != View.NO_ID) {
        mID = generateViewId();
    }
}
```
②通过if判断，检测子类是否调用父类的 onSaveInstanceState() 方法，否则会抛异常，突然看到这才明白，
还记得刚刚开始学Android的时候，如果一不小心把代码里面的 super.onCreate(savedInstanceState);
这行代码删掉，报了错误还看不懂，原来系统在这里检测了。
③container.put(mID, state)这行代码，将state放进SparseArray中，以view自身的id为key，
并且从注释来看打印mID的Hex值用来保证每页的id必须是唯一的，难怪每当我给view取id的时候，一个页面有重复的id就会报错，
谷歌在这里做判断了，不让我共用id，原来是想把id做为key来使用。
④走到这onSaveInstanceState()，调用如下代码：
```
@CallSuper
protected Parcelable onSaveInstanceState() {
    mPrivateFlags |= PFLAG_SAVE_STATE_CALLED;
    ......
    return BaseSavedState.EMPTY_STATE;
}
```
设置位标志， 默认不save任何东西，状态为空，这就是为啥我们每次随便写个类继承activity实现onCreate方法的时候可以
使用参数savedInstanceState保存状态，
因为默认为null，代码如下：
```
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    savedInstanceState.putString("key","value");
}
```
至此整个 onSaveInstanceState 保存状态源码分析完毕。




