package com.ishiqing.modules.ui_process.imageloader.cache;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ishiqing.R;
import com.sq.library.utils.AppUtils;

import org.junit.Test;

/**
 * Bitmap的加载和Cache
 * <p>
 * Created by javakam on 2018/9/4.
 */
public class BitmapUtils {

    @Test
    protected void testBitmap() {
        //前两者间接调用了 decodeStream
//        BitmapFactory.decodeFile();
//        BitmapFactory.decodeResource();
//        BitmapFactory.decodeStream();
//        BitmapFactory.decodeByteArray();

        BitmapUtils.decodeSampleBitmapFromSource(AppUtils.getContext().getResources()
                , R.mipmap.ic_launcher, 150, 180);
    }

    public static Bitmap decodeSampleBitmapFromSource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId);
        options.inSampleSize = caculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId);
    }

    private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int rawWidth = options.outWidth;
        final int rawHeight = options.outHeight;
        int inSampleSize = 1;

        if (rawWidth > reqWidth || rawHeight > reqHeight) {
            final int halfWidth = rawWidth / 2;
            final int halfHeight = rawHeight / 2;

            while ((halfWidth / inSampleSize) >= reqWidth && (halfHeight / inSampleSize) >= reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
