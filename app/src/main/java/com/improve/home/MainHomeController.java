package com.improve.home;

import android.content.Context;

import com.improve.R;
import com.improve.common.DataManager;

/**
 * Created by javakam on 2018/6/16.
 */
public class MainHomeController extends MainController {
    public MainHomeController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return getContext().getString(R.string.nav_main);
    }

    @Override
    protected MainItemAdapter getItemAdapter() {
        return new MainItemAdapter(getContext(), DataManager.getHomeGridDescriptions());
    }
}
