package com.improve.modules.ui_hencoder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.improve.R;


/**
 * 《HenCoder Android 开发进阶：UI 1-1 绘制基础》 的练习项目 https://github.com/hencoder/PracticeDraw1
 * <p>
 * 扔物线 - 朱凯 Blog：http://hencoder.com/ui-1-1/
 * <p>
 * My GitHub：https://github.com/javakam/Aimprove/tree/master/sqdraw
 * <p>
 * Created by javakam on 2018-5-19 Saturday.
 */
public class DrawMainActivity extends AppCompatActivity {
    private View myDrawView, myPathView, myPaintView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hencoder);
        myDrawView = findViewById(R.id.myDrawView);
        myPathView = findViewById(R.id.myPathView);
        myPaintView = findViewById(R.id.myPaintView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(DrawMainActivity.this, PracticeActivity.class));
            }
        }, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case android.R.id.home:
//                return true;
            case R.id.item_menu_canvas:
                myDrawView.setVisibility(View.VISIBLE);
                myPathView.setVisibility(View.GONE);
                myPaintView.setVisibility(View.GONE);
                break;
            case R.id.item_menu_path:
                myDrawView.setVisibility(View.GONE);
                myPathView.setVisibility(View.VISIBLE);
                myPaintView.setVisibility(View.GONE);
                break;
            case R.id.item_menu_paint:
                myDrawView.setVisibility(View.GONE);
                myPathView.setVisibility(View.GONE);
                myPaintView.setVisibility(View.VISIBLE);
                break;
            case R.id.item_menu_practice:
                startActivity(new Intent(this, PracticeActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
