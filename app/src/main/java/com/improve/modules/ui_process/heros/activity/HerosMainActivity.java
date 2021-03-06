package com.improve.modules.ui_process.heros.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.improve.R;

/**
 * 安卓群英传
 */
public class HerosMainActivity extends Activity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_heros);
        mIntent = new Intent(this, HeroViewActivity.class);
    }

    public void btnTeaching(View view) {
        mIntent.putExtra("flag", 0);
        startActivity(mIntent);
    }

    public void btnMyTextView(View view) {
        mIntent.putExtra("flag", 1);
        startActivity(mIntent);
    }

    public void btnShineTextView(View view) {
        mIntent.putExtra("flag", 2);
        startActivity(mIntent);
    }

    public void btnCircleProgress(View view) {
        mIntent.putExtra("flag", 3);
        startActivity(mIntent);
    }

    public void btnVolumeView(View view) {
        mIntent.putExtra("flag", 4);
        startActivity(mIntent);
    }

    public void btnMyScrollView(View view) {
        mIntent.putExtra("flag", 5);
        startActivity(mIntent);
    }

    public void btnTopBar(View view) {
        startActivity(new Intent(this, TopBarTest.class));
    }
}
