package com.ishiqing.modules.annotation;

import android.view.View;
import android.widget.Toast;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;

/**
 * Android注解开发
 * <p>
 * Created by javakam on 2018/6/20.
 */
public class AnnotationSwipeFragment extends BaseSwipeFragment {
    @ViewInject(R.id.btAnnotation)
    QMUIAlphaButton mBtAnno;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_annotation;
    }

    @Override
    protected void initViews(View v) {
        ViewUtils.inject(mActivity);
        initTopBar(UIRoute.FRAG_ANNOTATION, true);
    }

    @OnClick({R.id.btAnnotation})
    void click(View v) {
        Toast.makeText(mActivity, "Android注解开发", Toast.LENGTH_SHORT).show();
    }
}
