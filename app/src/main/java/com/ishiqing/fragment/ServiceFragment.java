package com.ishiqing.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by javakam on 2018/6/16.
 */
public class ServiceFragment extends BaseFragment {
    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, layout);
        return layout;
    }
}
