package com.improve.modules.kotlin;

import android.view.View;
import android.widget.TextView;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Title:KotlinSwipeFragment
 * <p>
 * Description:Kotlin
 * </p>
 *
 * @author Changbao
 * @date 2019/8/22 16:54
 */
public class KotlinSwipeFragment extends BaseSwipeFragment {

    @BindView(R.id.tvKotlin)
    TextView tvKotlin;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_kotlin;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_KOTLIN, true);
    }

    @OnClick({R.id.tvKotlin})
    void getNetConn(View v) {
        final int vid = v.getId();
        switch (vid) {
            case R.id.tvKotlin:

                break;
            default:
                break;
        }
    }

}
