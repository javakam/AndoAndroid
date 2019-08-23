package com.improve.modules.ui_process.heros;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.modules.ui_process.heros.activity.HerosMainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Android群英传
 * <p>
 * Created by javakam on 2018/7/17 0017.
 */
public class HeroSwipeFragment extends BaseSwipeFragment {
    @BindView(R.id.myScrollView)
    MyScrollView myScrollView;
    @BindView(R.id.myDrawerMenu)
    DrawerMenu myDrawerMenu;

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_heros;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("Android群英传", true);
    }


    @OnClick({R.id.btShowMyScrollView, R.id.btShowMyDrawerMenu, R.id.btHerosMainActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btShowMyScrollView:
                myScrollView.setVisibility(View.VISIBLE);
                myDrawerMenu.setVisibility(View.GONE);
                break;
            case R.id.btShowMyDrawerMenu:
                myScrollView.setVisibility(View.GONE);
                myDrawerMenu.setVisibility(View.VISIBLE);
                break;
            case R.id.btHerosMainActivity:
                startActivity(new Intent(mActivity, HerosMainActivity.class));
                break;
        }
    }
}
