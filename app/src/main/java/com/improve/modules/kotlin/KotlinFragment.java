package com.improve.modules.kotlin;

import android.view.View;
import android.widget.TextView;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * Title:KotlinFragment
 * <p>
 * Description:Kotlin
 * </p>
 *
 * @author Changbao
 * @date 2019/8/22 16:54
 */
public class KotlinFragment extends BaseSwipeFragment {

    private TextView tvKotlin;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_kotlin;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_KOTLIN, true);
        tvKotlin = v.findViewById(R.id.tvKotlin);

        v.findViewById(R.id.tvKotlin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int vid = v.getId();
        switch (vid) {
            case R.id.tvKotlin:

                break;
            default:
                break;
        }
    }

}