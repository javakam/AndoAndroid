package com.ishiqing.modules.activity;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.ishiqing.R;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.sq.library.utils.Base64Util;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;

/**
 * 加载 assets 下图片的 Base64 字符串
 *
 * @author javakam
 * @date 2018-6-20
 */
public class Base64SwipeFragment extends BaseSwipeFragment {
    @BindView(R.id.imgFromBase64Str)
    ImageView imgFromBase64Str;

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_base64;
    }

    @Override
    protected void initViews(View v) {
//        initTopBar("Base64Util", true);
        showLoadingEmptyView();
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideEmptyView();
            }
        }, 2000);

        //1 写Base64的图片到本地文件夹
        Bitmap bitmap;
        imgFromBase64Str.setImageBitmap(
                bitmap = Base64Util.base64ToBitmap(Base64Util.readAssetString(mActivity, "Img.txt")));

        Base64Util.base64BitmapToFile(bitmap, "", "xiaopang-big.jpg");
        //2 TXT 文本文件
        // 纯 byte方式
        String fileNameTxt = "文本文件.txt";
        byte[] bytesTxt = Base64Util.readAssetBytes(mActivity, fileNameTxt);
        byte[] encodeTxt = Base64.encode(bytesTxt, Base64.DEFAULT);
        byte[] decodeTxt = Base64.decode(encodeTxt, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decodeTxt, "", fileNameTxt);

        //3 写word文件到本地File
        //先把word编码成 base64 String

        //docx 格式不行。。
        String fileName1 = "文档111.docx";
        byte[] bytes = Base64Util.readAssetBytes(mActivity, fileName1);
        String encode64Str = Base64.encodeToString(bytes, Base64.DEFAULT);
        byte[] decode = Base64.decode(encode64Str, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode, "", fileName1);

        String fileName2 = "文档2222111.doc";
        byte[] bytes2 = Base64Util.readAssetBytes(mActivity, fileName2);
        String encode64Str2 = Base64.encodeToString(bytes2, Base64.DEFAULT);
        byte[] decode2 = Base64.decode(encode64Str2, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode2, "", fileName2);

        // 纯 byte方式
        String fileName3 = "纯文本文档.doc";
        byte[] bytes3 = Base64Util.readAssetBytes(mActivity, fileName3);
        byte[] encode3 = Base64.encode(bytes, Base64.DEFAULT);
        byte[] decode3 = Base64.decode(encode3, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode3, "", fileName3);

        // 将 encodeToString 返回的 String重新编码
        String fileName4 = "也是纯文本文档.doc";
        byte[] bytes4 = Base64Util.readAssetBytes(mActivity, fileName4);
        try {
            String org = new String(bytes4, "UTF-8");
            byte[] encodeTest = Base64Util.encodeTest(org);

            byte[] decode1 = Base64.decode(encodeTest, Base64.DEFAULT);
            String decodeTest = new String(decode1, "UTF-8");
            Base64Util.base64BytesToFile(decodeTest.getBytes(), "", fileName4);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


       /* byte[] encode = Base64.encode(bytes4, Base64.DEFAULT);
        try {
            String encode64Str4 = new String(encode, "GBK");
//        String encode64Str4 = Base64.encodeToString(bytes4, Base64.DEFAULT);
//        byte[] decode4 = Base64.decode(encode64Str4, Base64.DEFAULT);
            byte[] decode4 = new byte[0];
            decode4 = Base64.decode(new String(encode64Str4.getBytes("GBK")), Base64.DEFAULT);
            Base64Util.base64BytesToFile(decode4, "", fileName4);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }
}
