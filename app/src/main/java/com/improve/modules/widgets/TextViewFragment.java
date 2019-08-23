package com.improve.modules.widgets;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

import org.xml.sax.XMLReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 推荐：
 * 1   Android TextView中文字通过SpannableString来设置超链接、颜色、字体等属性
 * https://blog.csdn.net/snowdream86/article/details/6776629
 * 2   https://blog.csdn.net/xijiaohuangcao/article/details/7839856
 * <p>
 * Created by javakam on 2018/6/19.
 */
public class TextViewFragment extends BaseSwipeFragment {

    private TextView tvWidget1;
    private TextView tvWidget2;
    private TextView tvWidget3;
    private TextView tvWidget4;
    private TextView tvWidget5;
    private TextView tvWidget6;

    private static final String LINE = "\n ------------------------------------------> \n";

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_textview;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_WIDGET_TEXTVIEW, true);
        tvWidget1 = v.findViewById(R.id.tvWidget1);
        tvWidget2 = v.findViewById(R.id.tvWidget2);
        tvWidget3 = v.findViewById(R.id.tvWidget3);
        tvWidget4 = v.findViewById(R.id.tvWidget4);
        tvWidget5 = v.findViewById(R.id.tvWidget5);
        tvWidget6 = v.findViewById(R.id.tvWidget6);
        //        FF34B3  C5C1AA
        tvWidget1.setTextColor(Color.parseColor("#A0522D"));
        tvWidget2.setTextColor(Color.parseColor("#EE7621"));
        tvWidget3.setTextColor(Color.parseColor("#FF4040"));
        tvWidget6.setTextColor(Color.parseColor("#EE6363"));

        //1 使用URL、Email、电话等显示超链接
        //1.1   TextView设置属性 android:autoLink="all"
        StringBuilder sb1 = new StringBuilder();
        sb1.append("个人主页：https://www.jooy.top \n")  // 发现在4.0下，该项无效
                .append("我的邮箱：jooybao@foxmail.com \n")
                .append("我的电话： 10086").append(LINE);
        // 也可以通过动态代码实现
//        tvWidget1.setAutoLinkMask(Linkify.ALL);
        tvWidget1.setText(sb1.toString());
        //1.2 利用html标签实现超链接 --- 兼容 4.0 版本
        StringBuilder sb2 = new StringBuilder();
        sb2.append("<font color='red'>我的浏览器主页是：</font><br>  ")
                .append("<a href='https://www.jooy.top'>我的主页</a> <br>").append(LINE);
        Spanned spanned1 = Html.fromHtml(sb2.toString());
        tvWidget2.setText(spanned1);
        //注：必须设置上这句话才能跳转到浏览器
        tvWidget2.setMovementMethod(LinkMovementMethod.getInstance());
        //1.3 利用html标签插入图片   --  注意：图片不要加后缀类型 ic_launcher.png→ic_launcher
        StringBuilder sb3 = new StringBuilder();
        sb3.append("图片一： <img src='ic_launcher'/> <br>")
                .append(" 图片二： <img src='ic_tabbar_home'/><br>")
                .append(" 图片三： <img src='ic_launcher_round'/><br>")
                .append(LINE);
        Spanned spanned2 = Html.fromHtml(sb3.toString(), new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                /*
                这里有两种方式生成 Drawable 对象
                 */
                //1 通过 getResources().getIdentifier
//                int identifier = getResources().getIdentifier(source, "mipmap", mActivity.getPackageName());
//                Drawable drawable = getResources().getDrawable(identifier);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                //2 使用反射
                Drawable drawableField = null;
                try {
                    Field field = R.mipmap.class.getField(source);
                    int resId = Integer.parseInt(field.get(null).toString());
                    drawableField = getResources().getDrawable(resId);
                    drawableField.setBounds(0, 0, drawableField.getIntrinsicWidth()
                            , drawableField.getIntrinsicHeight());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
//                return drawable;
                return drawableField;
            }
        }, new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
//                System.out.println("opening = [" + opening + "], tag = [" + tag + "], " +
//                        "output = [" + output + "], xmlReader = [" + xmlReader + "]");
            }
        });
        tvWidget3.setText(spanned2);
        //1.4 实现部分文字点击事件
        tvWidget4.append("测试文本");
        String clickStr1 = "点击事件";
        String clickStr2 = "改变颜色";
        // 第一个 SpannableString
        SpannableString sp1 = new SpannableString(clickStr1);
        sp1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView textView = (TextView) widget;
                Toast.makeText(mActivity, "点击了 : " + textView.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#ff0000"));//设置文本颜色
            }
        }, 0, clickStr1.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 会覆盖掉 updateDrawState 设置的颜色 ↑
//        sp1.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")),
//                0, clickStr1.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp1.setSpan(new BackgroundColorSpan(Color.parseColor("#D4D6D8")),
                0, clickStr1.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvWidget4.append(sp1);
        tvWidget4.append("也是测试文本");
        // 第二个 SpannableString
        SpannableString sp2 = new SpannableString(clickStr2);
        sp2.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")),
                0, clickStr2.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvWidget4.append(sp2);
        tvWidget4.append("还是测试文本 \n");
        tvWidget4.setMovementMethod(LinkMovementMethod.getInstance());
        //1.5 实现走马灯效果
        String textLong = "Hone your skills on the latest cloud technologies with Google experts at hundreds of breakout sessions and interactive on-demand hands-on labs and bootcamps. You'll have the opportunity to engage with the best minds in cloud technology on how your industry is adapting, innovating, and growing with cloud.";
        tvWidget5.setText(textLong + LINE);
//        tvWidget5.setSelected(true);

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
                    .append("getAbsolutePath : ").append(cacheDir.getAbsolutePath())
                    .append(LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvWidget6.setText(sb6.toString());
    }
}