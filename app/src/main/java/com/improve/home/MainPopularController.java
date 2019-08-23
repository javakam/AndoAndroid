package com.improve.home;

import android.content.Context;

import com.improve.R;
import com.improve.common.SQDataManager;

/**
 * Created by javakam on 2018/7/17.
 */
public class MainPopularController extends MainController {
    public MainPopularController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return getContext().getString(R.string.nav_pop);
    }

    @Override
    protected MainItemAdapter getItemAdapter() {
        return new MainItemAdapter(getContext(), SQDataManager.getPopularDescriptions());
    }
}
