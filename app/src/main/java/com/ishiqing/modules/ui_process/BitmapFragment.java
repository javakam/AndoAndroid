package com.ishiqing.modules.ui_process;

import android.view.View;

import com.ishiqing.base.fragment.BaseSwipeFragment;

/**
 * Bitmap的加载和Cache
 * <p>
 * Created by javakam on 2018/8/16.
 */
public class BitmapFragment extends BaseSwipeFragment {
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initViews(View v) {
        bitmapDemo();
    }

    private void bitmapDemo() {

        //前两者间接调用了 decodeStream
//        BitmapFactory.decodeFile();
//        BitmapFactory.decodeResource();
//        BitmapFactory.decodeStream();
//        BitmapFactory.decodeByteArray();

//        BitmapFactory.Options
    }
}
