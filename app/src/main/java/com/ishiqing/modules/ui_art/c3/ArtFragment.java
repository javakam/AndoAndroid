package com.ishiqing.modules.ui_art.c3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Scroller;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import butterknife.OnClick;

/**
 * Android开发艺术探索
 * <p>
 * Created by javakam on 2018/7/17 .
 */
public class ArtFragment extends BaseFragment {

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_art;
    }

    @Override
    protected void initViews() {
        initTopBar("艺术探索", true);
    }

    @OnClick({R.id.btArt1, R.id.btArt2, R.id.btArt3, R.id.btArt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btArt1:
                startActivity(new Intent(mActivity, ArtTestActivity.class));
                break;
            case R.id.btArt2:
                break;
            case R.id.btArt3:
                break;
            case R.id.btArt4:
                Scroller scroller = new Scroller(getContext());
                scroller.computeScrollOffset();
                break;
        }
    }
}
