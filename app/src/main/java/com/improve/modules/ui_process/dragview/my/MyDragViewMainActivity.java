package com.improve.modules.ui_process.dragview.my;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.improve.R;


public class MyDragViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_dragview_my);
    }


    public void DragDemo(View view) {
        startActivity(getMyIntent("DragDemo"));
    }

    public void DragByScroller(View view) {
        startActivity(getMyIntent("DragByScroller"));
    }

    public void ViewDragHelperDemo(View view) {
        startActivity(getMyIntent("ViewDragHelperDemo"));
    }

    private Intent getMyIntent(String way) {
        Intent intent = new Intent(this, DragActivity.class);
        intent.putExtra("which", way);
        return intent;
    }
}
