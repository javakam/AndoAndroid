package com.improve.modules.thread;


import android.util.Log;

/**
 * Created by javakam on 2018/8/15.
 */
public class TestClassLoad {
    public static final String POS = "ABCD";

    static {
        Log.e("123", "hello world");
    }

    public static final String POS222 = "LOL";
}
