package com.improve.common;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.improve.R;
import com.improve.base.BaseQMUIFragmentActivity;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.home.MainSwipeFragment;

/**
 * @author changbao
 */
public class MainActivity extends BaseQMUIFragmentActivity {

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
