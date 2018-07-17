package com.ishiqing;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ishiqing.base.BaseFragment;
import com.ishiqing.base.BaseQMUIFragmentActivity;
import com.ishiqing.home.MainFragment;

public class MainActivityQMUI extends BaseQMUIFragmentActivity {

    @SuppressLint("ResourceType")
    @Override
    protected int getContextViewId() {
        return R.id.sqcomponent;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = new MainFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
