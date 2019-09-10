package com.improve.modules.ui_process.heros.activity;

import android.app.Activity;
import android.os.Bundle;

import com.improve.R;
import com.improve.modules.ui_process.heros.view.CircleProgressView;

public class HeroViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = getIntent().getIntExtra("flag", -1);
        switch (flag) {
            case 0:
                setContentView(R.layout.activity_hero_teaching);
                break;
            case 1:
                setContentView(R.layout.activity_hero_textview);
                break;
            case 2:
                setContentView(R.layout.activity_hero_shine_textview);
                break;
            case 3:
                setContentView(R.layout.activity_hero_circle_progress);
                CircleProgressView circle = (CircleProgressView) findViewById(R.id.circle);
                circle.setSweepValue(0);
                break;
            case 4:
                setContentView(R.layout.activity_hero_volume);
                break;
            case 5:
                setContentView(R.layout.activity_hero_scrollview);
                break;
            default:
                break;
        }
    }
}
