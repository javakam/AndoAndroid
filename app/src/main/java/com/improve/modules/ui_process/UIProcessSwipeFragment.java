package com.improve.modules.ui_process;

import android.support.annotation.NonNull;
import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;


/**
 * UI绘制流程  {@see UI绘制流程.md}
 *
 * @author javakam
 * @date 2018/6/27
 */
public class UIProcessSwipeFragment extends BaseSwipeFragment {
    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_ui_process;
    }

    @Override
    protected void initViews(View v) {
//        initTopBar("UI绘制流程", true);
        showLoadingEmptyView();
        showNetErrorEmptyView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideEmptyView();
            }
        }, 2000);

        View.MeasureSpec measureSpec = new View.MeasureSpec();
        View.MeasureSpec.getMode(1);
        View.MeasureSpec.getSize(1);

        // 2018年7月13日
        /*
        View.inflate() 中：

         LayoutInflater factory = LayoutInflater.from(context);
         return factory.inflate(resource, root);

         最终调用的是 LayoutInflater中的：
         public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {...}
         详见：LayouInflater源码.md
         */
    }
}
