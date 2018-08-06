package com.ishiqing.modules.window;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.ishiqing.R;
import com.ishiqing.base.BaseActivity;

public class WindowActivity extends BaseActivity {
    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_window;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initTopBar("WindowManager", true);

        Button button = new Button(this);
        button.setText("button...");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);

        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        // TYPE_SYSTEM_OVERLAY 过期了 -》 TYPE_APPLICATION_OVERLAY         黑屏。。。WNM
//        layoutParams.flags = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        layoutParams.gravity = Gravity.LEFT | Gravity.CENTER;
        layoutParams.x = 100;
        layoutParams.y = 200;
        WindowManager wm = getWindowManager();
        wm.addView(button, layoutParams);

        /*
        利用ViewManager中的
        updateViewLayout(View view, ViewGroup.LayoutParams params)
        不断更新视图位置
         */
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        layoutParams.x = rawX;
                        layoutParams.y = rawY;
                        wm.updateViewLayout(button, layoutParams);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

    }
}
