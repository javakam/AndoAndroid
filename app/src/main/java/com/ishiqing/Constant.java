package com.ishiqing;

import com.sq.library.Global;
import com.sq.library.utils.AppUtils;

/**
 * Created by javakam on 2018/6/21.
 */
public class Constant {
    private static final String APP_FILE_NAME = Global.APP_FILE_NAME;

    /*****************************存储缓存数据****************************/
    private static final String PATH_CACHE = AppUtils.getContext().getCacheDir().getPath();
    private static final String CACHE_DIR = APP_FILE_NAME + "/SQCache";

}
