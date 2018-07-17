package com.ishiqing.base;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * SingleTask {@see 安全退出已调用多个Activity的App.md}
 * <p>
 * Created by javakam on 2018/7/14.
 */
public class EG_SingleTaskActivity extends AppCompatActivity {
    private boolean mIsExit;

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
    退出
    Intent intent = new Intent(this,DrawMainActivity.class);
    intent.putExtra(DrawMainActivity.TAG_EXIT, true);
    startActivity(intent);
     */
    private static final String TAG_EXIT = "exit";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }

}
