### 如何安全退出已调用多个Activity的Application？
---
#### Activity的管理方式
- （一）容器式  **com.improve.base.BaseActivity**
- （二）SingleTask  **com.improve.base.EG_SingleTaskActivity**
1.设置MainActivity的加载模式为singleTask
```
android:launchMode="singleTask"
```
2、将退出出口放置在MainActivity
```
private boolean mIsExit;
/**双击返回键退出*/
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
        if (mIsExit) {
            this.finish();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mIsExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);
        }
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
```
改进版，第二步重写onNewIntent()方法
```
private static final String TAG_EXIT = "exit";
@Override
protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    if (intent != null) {
        boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
        if (isExit) {
            this.finish();
        }
    }
}
```
第三步 退出
```
Intent intent = new Intent(this,MainActivity.class);
intent.putExtra(MainActivity.TAG_EXIT, true);
startActivity(intent);
```