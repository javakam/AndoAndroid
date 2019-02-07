package com.improve.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FontUtils {

    private FontUtils() {
    }

    public static void initFont(Context context, String fontAssetName) {
        Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont("MONOSPACE", regular);
    }

    private static void replaceFont(String staticTypefaceFieldName, Typeface newTypeface) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap();
            newMap.put(staticTypefaceFieldName, newTypeface);

            try {
                Field staticField = Typeface.class.getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (IllegalAccessException | NoSuchFieldException var5) {
                var5.printStackTrace();
            }
        } else {
            try {
                Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }
    }
}