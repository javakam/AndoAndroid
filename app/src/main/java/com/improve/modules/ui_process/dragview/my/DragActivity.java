package com.improve.modules.ui_process.dragview.my;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.improve.R;


public class DragActivity extends AppCompatActivity {
    private static String which;
    private View myView;
    @Nullable
    private Button btMain, btMenu;
    @Nullable
    private ViewDragHelperDemo viewDragHelper;

    public void initVariables() {
        Intent intent = getIntent();
        which = intent.getStringExtra("which");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        if (which.equals("DragDemo")) {
            setContentView(R.layout.activity_drag);
            myView = findViewById(R.id.dragDemo);
        } else if (which.equals("DragByScroller")) {
            setContentView(R.layout.activity_drag_scroller);
            myView = findViewById(R.id.dragScroller);
        } else if (which.equals("ViewDragHelperDemo")) {
            setContentView(R.layout.activity_drag_helper);
            viewDragHelper = findViewById(R.id.viewDragHelper);
            btMain = findViewById(R.id.btMain);
            btMain.setOnClickListener(v -> {
//                viewDragHelper.computeScroll();
            });
        }
    }

    @Nullable
    public void startScroll(View view) {
        if (which.equals("DragDemo")) {
            ((View) myView.getParent()).
                    scrollBy(-50, -60);
        } else if (which.equals("DragByScroller")) {
            ((View) myView.getParent()).
                    scrollBy(-150, -160);
        }
    }
}
