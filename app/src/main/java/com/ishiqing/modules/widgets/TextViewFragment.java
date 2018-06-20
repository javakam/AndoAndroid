package com.ishiqing.modules.widgets;

import android.graphics.Color;
import android.os.Environment;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;

/**
 * Created by javakam on 2018/6/19.
 */
public class TextViewFragment extends BaseFragment {
    @BindView(R.id.tvWidget1)
    TextView tvWidget1;
    @BindView(R.id.tvWidget2)
    TextView tvWidget2;
    @BindView(R.id.tvWidget3)
    TextView tvWidget3;
    @BindView(R.id.tvWidget4)
    TextView tvWidget4;
    @BindView(R.id.tvWidget5)
    TextView tvWidget5;
    @BindView(R.id.tvWidget6)
    TextView tvWidget6;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_textview;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_WIDGET_TEXTVIEW, true);
        //  A0522D  EE7621  FF4040  FF34B3  C5C1AA
        tvWidget1.setBackgroundColor(Color.parseColor("#A0522D"));
        tvWidget6.setBackgroundColor(Color.parseColor("#EE6363"));

        //1 使用URL、Email、电话等显示超链接
        //  TextView设置属性 android:autoLink="all"
        StringBuilder sb1 = new StringBuilder();
        sb1.append("个人主页：https://www.jooy.top \n")
                .append("我的邮箱：jooybao@foxmail.com \n")
                .append("我的电话： 10086 ");
        tvWidget1.setText(sb1.toString());

        //6 android 文件路径/默认缓存路径
        StringBuilder sb6 = new StringBuilder();
        File dir = Environment.getExternalStorageDirectory();
        File cacheDir = getContext().getCacheDir();
        try {
            sb6.append("Android路径说明: \n").append("File dir = Environment.getExternalStorageDirectory();")
                    .append("getName : ").append(dir.getName()).append("\n")
                    .append("getPath : ").append(dir.getPath()).append("\n")
                    .append("getAbsolutePath : ").append(dir.getAbsolutePath()).append("\n")
                    .append("getCanonicalPath : ").append(dir.getCanonicalPath()).append("\n")
                    .append("getParent : ").append(dir.getParent()).append("\n")
                    .append("getTotalSpace : ").append(dir.getTotalSpace()).append("\n")
                    .append("getFreeSpace : ").append(dir.getFreeSpace()).append("\n")
                    .append("getUsableSpace : ").append(dir.getUsableSpace()).append("\n")
                    .append(" ------------------------------>  ").append("\n")
                    .append("File cacheDir = getContext().getCacheDir();\n")
                    .append("getName : ").append(cacheDir.getName()).append("\n")
                    .append("getPath : ").append(cacheDir.getPath()).append("\n")
                    .append("getAbsolutePath : ").append(cacheDir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvWidget6.setText(sb6.toString());
    }
}