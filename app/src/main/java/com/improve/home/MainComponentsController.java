package com.improve.home;

import android.content.Context;

import com.improve.R;
import com.improve.common.SQDataManager;

/**
 * Created by javakam on 2018/6/16.
 */
public class MainComponentsController extends MainController {
    public MainComponentsController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return getContext().getString(R.string.nav_component);
    }

    @Override
    protected MainItemAdapter getItemAdapter() {
        return new MainItemAdapter(getContext(), SQDataManager.getComponentsDescriptions());
    }
}
