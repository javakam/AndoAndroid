package com.improve.modules.ui_process.art.c3.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

import butterknife.OnClick;

/**
 * Android开发艺术探索
 * <p>
 * Created by javakam on 2018/7/17 .
 */
public class ArtSwipeFragment extends BaseSwipeFragment {

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_art;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("艺术探索", true);
    }

    @OnClick({R.id.btArt1, R.id.btArt2, R.id.btArt3, R.id.btArt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
        }
    }
}
