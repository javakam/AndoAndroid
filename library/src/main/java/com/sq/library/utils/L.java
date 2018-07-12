package com.sq.library.utils;

import android.util.Log;

/**
 * Log 统一管理类
 * <p>
 * Created by javakam on 2018-7-9 .
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "123";
    private static final int LOG_MAXLENGTH = 5000;


    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    /**
     * 截断输出日志
     *
     * @param msg
     */
    public static void ee(String msg) {
        if (isDebug) {
            if (msg == null || msg.length() == 0) {
                return;
            }
            int segmentSize = 5 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.e(TAG, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.e(TAG, logContent);
                }
                Log.e(TAG, msg);// 打印剩余日志
            }
        }
    }

    public static void ww(String msg) {
        if (isDebug) {
            if (msg == null || msg.length() == 0) {
                return;
            }
            int segmentSize = 5 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.w(TAG, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.w(TAG, logContent);
                }
                Log.w(TAG, msg);// 打印剩余日志
            }
        }
    }

    public static void dd(String msg) {
        if (isDebug) {
            if (msg == null || msg.length() == 0) {
                return;
            }
            int segmentSize = 5 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.d(TAG, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.d(TAG, logContent);
                }
                Log.d(TAG, msg);// 打印剩余日志
            }
        }
    }
}