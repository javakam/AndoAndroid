package com.ishiqing.modules.activity;

import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.ImageView;

import com.ishiqing.R;
import com.ishiqing.base.BaseActivity;
import com.ishiqing.utils.Base64Util;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;

/**
 * @author javakam
 * @date 2018-6-20
 */
public class SampleActivity1 extends BaseActivity {
    @BindView(R.id.imgFromBase64Str)
    ImageView imgFromBase64Str;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample1;
    }

    @Override
    protected void initViews() {
        showLoadingEmptyView();
        mEmptyView.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideEmptyView();
            }
        }, 2000);

        //1 写Base64的图片到本地文件夹
        Bitmap bitmap;
        imgFromBase64Str.setImageBitmap(
                bitmap = Base64Util.base64ToBitmap(Base64Util.readAssetString(this, "Img.txt")));

        Base64Util.base64BitmapToFile(bitmap, "", "xiaopang-big.jpg");

        //2 写word文件到本地File
        //先把word编码成 base64 String

        //docx 格式不行。。
        String fileName1 = "文档111.docx";
        byte[] bytes = Base64Util.readAssetBytes(this, fileName1);
        String encode64Str = Base64.encodeToString(bytes, Base64.DEFAULT);
        byte[] decode = Base64.decode(encode64Str, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode, "", fileName1);

        String fileName2 = "文档2222111.doc";
        byte[] bytes2 = Base64Util.readAssetBytes(this, fileName2);
        String encode64Str2 = Base64.encodeToString(bytes2, Base64.DEFAULT);
        byte[] decode2 = Base64.decode(encode64Str2, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode2, "", fileName2);

        // 纯 byte方式
        String fileName3 = "纯文本文档.doc";
        byte[] bytes3 = Base64Util.readAssetBytes(this, fileName3);
        byte[] encode3 = Base64.encode(bytes, Base64.DEFAULT);
        byte[] decode3 = Base64.decode(encode3, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode3, "", fileName3);

        // 将 encodeToString 返回的 String重新编码
        String fileName4 = "也是纯文本文档.doc";
        byte[] bytes4 = Base64Util.readAssetBytes(this, fileName4);
        String encode64Str4 = Base64.encodeToString(bytes4, Base64.DEFAULT);
        try {
            byte[] gb2312s = encode64Str4.getBytes("GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] decode4 = Base64.decode(encode64Str4, Base64.DEFAULT);
        Base64Util.base64BytesToFile(decode4, "", fileName4);
    }
}
