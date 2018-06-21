package com.ishiqing;

import android.os.Environment;

import com.ishiqing.utils.AppUtils;

/**
 * Created by javakam on 2018/6/21.
 */
public class Constant {
    private static final String APP_FILE_NAME = "/AQ";
    /*****************************存储文件*******************************/
    private static final String PATH_EXTERNAL = Environment.getExternalStorageDirectory().getPath();
    public static final String PATH_FILE = PATH_EXTERNAL + APP_FILE_NAME + "/File";
    public static final String PATH_IMAGE = PATH_EXTERNAL + APP_FILE_NAME + "/Image";

    /*****************************存储缓存数据****************************/
    private static final String PATH_CACHE = AppUtils.getContext().getCacheDir().getPath();
    private static final String CACHE_DIR = APP_FILE_NAME + "/SQCache";


}
