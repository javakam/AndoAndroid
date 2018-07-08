package com.sq.library.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * SharedPreferences 工具类
 * Created by javakam on 2018-7-8.
 */

public class SharePrefUtil {

//    public static final String PREF_NAME = Global.SP_FILE_NAME;

    public static SharedPreferences sharePrefer;

    public static void initSharePreference(Context context, String prefName) {
        if (sharePrefer != null) {
            return;
        }
        if (sharePrefer == null) {
            sharePrefer = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }
    }

    private SharePrefUtil() {
    }

    /**
     * 保存数据
     *
     * @param key
     * @param data
     */
    public static void saveData(String key, Object data) {
        if (sharePrefer == null)
            return;
        String type = data.getClass().getSimpleName();
        SharedPreferences.Editor editor = sharePrefer.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.apply();
    }

    /**
     * 读数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public static Object getData(String key, Object defValue) {
        if (sharePrefer == null) {
            return null;
        }
        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return sharePrefer.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharePrefer.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharePrefer.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharePrefer.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharePrefer.getLong(key, (Long) defValue);
        }
        return null;
    }

}
