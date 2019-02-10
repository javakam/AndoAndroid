package com.improve.modules.ui_process;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.View;

import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.cache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * LruCache & DiskLruCache
 * <p>
 * 郭霖 -- Android高效加载大图、多图解决方案，有效避免程序OOM   https://blog.csdn.net/guolin_blog/article/details/9316683
 * 郭霖 -- Android DiskLruCache完全解析，硬盘缓存的最佳方案   https://blog.csdn.net/guolin_blog/article/details/28863651
 * <p>
 * Created by javakam on 2018/9/6.
 */
public class LruCacheFragment extends BaseSwipeFragment {

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initViews(View v) {
        //LruCache初始化及一些基本操作
        lruCache();
        mMemoryCache.put("", null);
        mMemoryCache.get("");
        mMemoryCache.remove("");

        diskLruCache();

    }

    /******************************************DISKLRUCACHE*********************************************/
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;//50M
    private static final int DISK_CACHE_INDEX = 0;
    private DiskLruCache mDiskLruCache;

    private void diskLruCache() {
        File discCacheDir = getDiskCacheDir(mActivity, "bitmap");
        if (!discCacheDir.exists()) {
            discCacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(discCacheDir, 1, 1, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void aboutDiskLruCache(String url) {
        String key = hashKeyFormUrl(url);
        try {
            //1 DiskLruCache写入文件
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            OutputStream newOutputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            editor.set(1, "");
            editor.getString(1);
            editor.commit();//真正将下载的文件写入文件系统
            editor.abort();//如果下载文件异常，退出整个操作
            mDiskLruCache.flush();

            //2 DiskLruCache的缓存查找
            /*
            @see ImageResizer.decodeSampledBitmapFromFileDescriptor
             */
//            BitmapFactory.decodeFileDescriptor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDiskCacheDir(Context context, String bitmap) {
        return null;
    }

    private String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /******************************************LRUCACHE*********************************************/
    LruCache<String, Bitmap> mMemoryCache;

    private void lruCache() {
         /*
        开发中，/ 1024  是为了将 byte 转换为 KB

        重写 sizeof 计算缓存对象的大小

        总容量cacheSize大小为当前进程可用内存的1/8
         */
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                return super.sizeOf(key, value);
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }
}
