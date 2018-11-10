package com.ishiqing.modules.window;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.BaseActivity;

public class WindowActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_window;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initTopBar("WindowManager", true);

        Button button = new Button(this);
        button.setText("动态添加Button");
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
//        wm.addView(button, layoutParams);

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


        button.setOnClickListener(v -> {
            showCusDialog();
        });
    }

    /**
     * 普通的Dialog需要Activity的Context才能启动，如果使用 Application的Context会报错:
     * <p>
     * android.view.WindowManager$BadTokenException: Unable to add window -- token null is not for an application
     * <p>
     * 需要设置成系统window才能显示： dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);
     * <p>
     * 另外，系统的Dialog比较特殊，不需要token
     */
    private void showCusDialog() {
        Dialog dialog = new Dialog(getApplicationContext());// this
        TextView textView = new TextView(this);
        textView.setBackgroundColor(Color.YELLOW);
        textView.setText("this is a dialog");
        dialog.setContentView(textView);
        // 神奇的一幕发生了。。。在使用 TYPE_SYSTEM_OVERLAY 时，会弹出一个回退键无法关闭的系统级的Dialog！！！
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);//
        dialog.show();
    }
}
