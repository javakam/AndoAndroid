package com.improve.modules.ui_process.heros;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.modules.ui_process.heros.activity.HerosMainActivity;

/**
 * Android群英传
 * <p>
 * Created by javakam on 2018/7/17 0017.
 */
public class HeroFragment extends BaseSwipeFragment {

    private MyScrollView myScrollView;
    private DrawerMenu myDrawerMenu;

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_heros;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("Android群英传", true);
        myScrollView = v.findViewById(R.id.myScrollView);
        myDrawerMenu = v.findViewById(R.id.myDrawerMenu);

        v.findViewById(R.id.btShowMyScrollView).setOnClickListener(this);
        v.findViewById(R.id.btShowMyDrawerMenu).setOnClickListener(this);
        v.findViewById(R.id.btHerosMainActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            default:
                break;
        }
    }
}
