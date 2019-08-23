package com.improve.modules.widgets;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * FlexboxLayout {@link CatFlexboxLayoutFragment }
 * <p>
 * https://www.jianshu.com/p/b3a9c4a99053
 * 单选多选 https://blog.csdn.net/LXLYHM/article/details/78753558
 * <p>
 * <p>
 * https://github.com/google/flexbox-layout
 * <p>
 * FlexboxlayoutManager是支持View回收的，而FlexboxLayout是不支持View回收的，
 * FlexboxLayout 只适用于少量Item的场景，这也是为什么会出现FlexboxLayoutManager的原因吧
 *
 * @author javakam
 * @date 2018/7/6.
 */
public class FlexboxLayoutFragment extends BaseSwipeFragment {
    FlexboxLayout mFlexboxLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_flexbox;
    }

    @Override
    protected void initViews(View v) {
        mFlexboxLayout = v.findViewById(R.id.flexbox);

        v.findViewById(R.id.textviewDynamic).setOnClickListener(this);
    }

    /**
     * 演示的效果
     * <p>
     * one two three 横向平局分
     * <p>
     * 1.子元素占主轴空间的百分比                 app:layout_flexBasisPercent="33%"
     * 2.相当于LinearLayout中的weight           app:layout_flexGrow="1"
     * 3.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTipDialogUtil.createIconWithTipDialog(mActivity, QMUITipDialog.Builder.ICON_TYPE_LOADING
                , "Loading...");
        mFlexboxLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTipDialogUtil.dismiss();
            }
        }, 1500);
    }

    int i = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {  // 通过代码向 FlexboxLayout 添加View
        TextView textView = new TextView(mActivity);
        textView.setBackground(getResources().getDrawable(R.drawable.flex_item_background));
        textView.setText("Dynamic Label" + (i = i + 3));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(30, i, 30, i);
        textView.setTextColor(getResources().getColor(android.R.color.holo_purple));
        mFlexboxLayout.addView(textView);
        //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
        ViewGroup.LayoutParams params = textView.getLayoutParams();
        if (params instanceof FlexboxLayout.LayoutParams) {
            FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
            layoutParams.setFlexBasisPercent(0.35f);
        }
    }

}