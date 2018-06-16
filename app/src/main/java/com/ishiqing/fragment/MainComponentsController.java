package com.ishiqing.fragment;

import android.content.Context;

import com.ishiqing.R;
import com.ishiqing.manager.SQDataManager;

/**
 * Created by javakam on 2018/6/16.
 */
public class MainComponentsController extends MainController {
    public MainComponentsController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return getContext().getString(R.string.nav_main);
    }

    @Override
    protected MainItemAdapter getItemAdapter() {
        return new MainItemAdapter(getContext(), SQDataManager.getComponentsDescriptions());
    }
}
