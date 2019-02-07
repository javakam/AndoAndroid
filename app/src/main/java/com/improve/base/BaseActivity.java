package com.improve.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.improve.R;
import com.improve.utils.L;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by javakam on 2018/6/20.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.topbar)
    protected QMUITopBar mTopBar;
    @Nullable
    @BindView(R.id.emptyView)
    protected QMUIEmptyView mEmptyView;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //恢复全局的数据
        if (savedInstanceState != null) {
            recoverInstanceState(savedInstanceState);
        }
        super.onCreate(savedInstanceState);
        //Activity的管理方式（一） -- 容器式
        //缺陷：当一个嵌套比较深的已存入Stack中的Activity被系统杀死时，并没有被移出栈，造成内存泄漏！
        //建立一个全局容器，把所有的Activity存储起来，退出时循环遍历finish所有Activity
        ActivityManager.getActivityManager().addActivity(this);
        //Activity的管理方式（二） -- SingleTask

        QMUIStatusBarHelper.translucent(this);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initViews(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        // 从栈中移除该Activity & 结束Activity
        ActivityManager.getActivityManager().finishActivity();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 获取系统保存的数据
     */
    private void recoverInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("name")) {
            String name = savedInstanceState.getString("name");
            L.i("recoveryInstanceState : " + name);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", "martin");
        L.i("onSaveInstanceState : martin");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        L.i("onRestoreInstanceState : " + savedInstanceState.getString("name"));
    }

    /**
     * 初始化View
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 可以通过重写该方法，重新定义TopBar事件
     */
    public void initTopBar(String title, boolean showImageButtonBack) {
        if (mTopBar != null) {
            mTopBar.setTitle(title);
            if (showImageButtonBack) {
                mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
    }

    /**
     * loading
     */
    public void showLoadingEmptyView() {
        if (mEmptyView != null) {
            mEmptyView.show(true);
        }
    }

    public void hideEmptyView() {
        if (mEmptyView != null) {
            mEmptyView.hide();
        }
    }

    /**
     * 显示联网异常的 QMUIEmptyView
     *
     * @param onClickListener 按钮的onClick监听，不需要则传null
     */
    public void showNetErrorEmptyView(@Nullable View.OnClickListener onClickListener) {
        mEmptyView.show(false, //是否要显示loading
                //标题的文字，不需要则传null
                getResources().getString(R.string.emptyView_mode_desc_fail_title),
                // 详情文字，不需要则传null
                getResources().getString(R.string.emptyView_mode_desc_fail_desc),
                //按钮的文字，不需要按钮则传null
                getResources().getString(R.string.emptyView_mode_desc_retry),
                onClickListener);
    }


}
