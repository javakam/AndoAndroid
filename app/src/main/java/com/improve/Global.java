package com.improve;

import android.os.Environment;

/**
 * Created by javakam on 2018/7/8.
 */
public class Global {
    public static final String APP_FILE_NAME = "/AndroidImprove";

    /*****************************存储文件*******************************/
    private static final String PATH_EXTERNAL = Environment.getExternalStorageDirectory().getPath();
    public static final String PATH_FILE = PATH_EXTERNAL + APP_FILE_NAME + "/File";
    public static final String PATH_IMAGE = PATH_EXTERNAL + APP_FILE_NAME + "/Image";

    public static final String SP_FILE_NAME = "pref_sq_config";

    public static final String DIALOG_LOADING = "Loading...";
}
