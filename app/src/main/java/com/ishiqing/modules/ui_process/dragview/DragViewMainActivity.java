package com.ishiqing.modules.ui_process.dragview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.modules.ui_process.dragview.my.MyDragViewMainActivity;


public class DragViewMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dragview);
    }

    public void btnView(View view) {
        startActivity(new Intent(this, DragViewActivity.class));
    }

    public void btnViewGroup(View view) {
        startActivity(new Intent(this, DragViewGroupActivity.class));
    }

    public void btnMyViewGroup(View v) {
        startActivity(new Intent(this, MyDragViewMainActivity.class));
    }
}
