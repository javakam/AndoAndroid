package com.ishiqing.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by javakam on 2018/6/20.
 */
public class Base64Util {
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/AiSQ/File/images";

    /**
     * 读取本地 Assets 目录下的文件
     *
     * @param context
     * @param fileName 文件名
     * @return
     */
    public static byte[] readAssetBytes(@NonNull Context context, @NonNull String fileName) {
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            return new byte[is.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取本地 Assets 目录下的文件
     *
     * @param context
     * @param fileName 文件名
     * @return
     */
    public static String readAssetString(@NonNull Context context, @NonNull String fileName) {
        InputStream is = null;
        String msg = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            msg = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * base64转换为Bitmap
     *
     * @param @param  base64String
     * @param @return 设定文件
     * @return Bitmap    返回类型
     */
    public static Bitmap base64ToBitmap(String base64String) {
        byte[] decode = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    /**
     * @param base64String
     * @return
     */
    public static void base64BitmapToFile(String base64String) {
    }

    /**
     * 将 byte[] 字节数据转成 Bitmap 存放到本地
     * 2018年6月13日15:57:10
     *
     * @param filePath
     * @param fileName
     */
    public static void base64BitmapToFile(byte[] bytes, String filePath, String fileName) {
        // todo test
        filePath = FILE_PATH;

        File listnames = new File(filePath);
        if (!listnames.exists()) {
            listnames.mkdirs();
        } else if (!listnames.isDirectory() && listnames.canWrite()) {
            listnames.delete();
            listnames.mkdirs();
        } else {
            //you can't access there with write permission.
            //Try other way.
        }
        File file = new File(listnames, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);//参数100表示不压缩
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 Bitmap 直接存放到本地
     * 2018年6月13日15:57:10
     *
     * @param filePath
     * @param fileName
     */
    public static void base64BitmapToFile(@NonNull Bitmap bitmap, String filePath, String fileName) {
        // todo test
        filePath = FILE_PATH;
        File listnames = new File(filePath);
        if (!listnames.exists()) {
            listnames.mkdirs();
        } else if (!listnames.isDirectory() && listnames.canWrite()) {
            listnames.delete();
            listnames.mkdirs();
        } else {
            //you can't access there with write permission.
            //Try other way.
        }
        File file = new File(listnames, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);//参数100表示不压缩
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 Base64格式的 String 直接存放到本地
     *
     * @param base64Str
     * @param filePath
     * @param fileName
     */
    public static void base64StrToFile(String base64Str, String filePath, String fileName) {
        // todo test
        filePath = FILE_PATH;
        File listnames = new File(filePath);
        if (!listnames.exists()) {
            listnames.mkdirs();
        } else if (!listnames.isDirectory() && listnames.canWrite()) {
            listnames.delete();
            listnames.mkdirs();
        } else {
            //you can't access there with write permission.
            //Try other way.
        }
        File file = new File(listnames, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);//参数100表示不压缩
            byte[] decode = android.util.Base64.decode(base64Str, android.util.Base64.DEFAULT);
            fos.write(decode, 0, decode.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 android.util.Base64 解码后的 byte[] 写成相应文件存放到本地
     *
     * @param bytes    Base64解码后的文件的内容（字节数组）
     * @param filePath
     * @param fileName
     */
    public static void base64BytesToFile(@NonNull byte[] bytes, String filePath, String fileName) {
        // todo test
        filePath = FILE_PATH;
        File listnames = new File(filePath);
        if (!listnames.exists()) {
            listnames.mkdirs();
        } else if (!listnames.isDirectory() && listnames.canWrite()) {
            listnames.delete();
            listnames.mkdirs();
        } else {
            //you can't access there with write permission.
            //Try other way.
        }
        File file = new File(listnames, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);//参数100表示不压缩
            fos.write(bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
