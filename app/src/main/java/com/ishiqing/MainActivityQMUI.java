package com.ishiqing;

import android.os.Bundle;

import com.ishiqing.base.BaseFragment;
import com.ishiqing.base.BaseQMUIFragmentActivity;
import com.ishiqing.fragment.MainFragment;

public class MainActivityQMUI extends BaseQMUIFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.sqcomponent;
    }

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
