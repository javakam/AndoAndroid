package com.improve;


import android.os.Environment;

import com.improve.utils.AppUtils;

/**
 * Created by javakam on 2018/6/21.
 */
public class Constant {
    public static final String APP_FILE_NAME = "/AndroidImprove";
    public static final String SP_FILE_NAME = "pref_sq_config";
    public static final String DIALOG_LOADING = "Loading...";

    /*****************************存储缓存数据****************************/
    private static final String PATH_CACHE = AppUtils.getContext().getCacheDir().getPath();
    private static final String CACHE_DIR = APP_FILE_NAME + "/Cache";

    /*****************************存储文件*******************************/
    private static final String PATH_EXTERNAL = Environment.getExternalStorageDirectory().getPath();
    public static final String PATH_IMAGE = PATH_EXTERNAL + APP_FILE_NAME + "/Image";
    public static final String PATH_FILE = PATH_EXTERNAL + APP_FILE_NAME + "/File";
}
