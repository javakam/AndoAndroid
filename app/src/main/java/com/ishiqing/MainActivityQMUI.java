package com.ishiqing;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ishiqing.base.BaseQMUIFragmentActivity;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.ishiqing.home.MainSwipeFragment;

/**
 * @author changbao
 */
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
            BaseSwipeFragment fragment = new MainSwipeFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
