package com.improve.modules.ui_process.art.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * Android开发艺术探索
 * <p>
 * Created by javakam on 2018/7/17 .
 */
public class ArtFragment extends BaseSwipeFragment {

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_art;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("艺术探索", true);

        v.findViewById(R.id.btArt1).setOnClickListener(this);
        v.findViewById(R.id.btArt2).setOnClickListener(this);
        v.findViewById(R.id.btArt3).setOnClickListener(this);
        v.findViewById(R.id.btArt4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btArt1:
                startActivity(new Intent(mActivity, ArtTestActivity.class));
                break;
            case R.id.btArt2:
                startActivity(new Intent(mActivity, ArtDemoActivity_1.class));
                break;
            case R.id.btArt3:
                startActivity(new Intent(mActivity, ArtDemoActivity_2.class));
                break;
            case R.id.btArt4:
                break;
            default:
                break;
        }
    }
}
