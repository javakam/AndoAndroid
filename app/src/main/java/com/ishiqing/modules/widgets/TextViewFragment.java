package com.ishiqing.modules.widgets;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import org.xml.sax.XMLReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * 推荐：
 * 1   Android TextView中文字通过SpannableString来设置超链接、颜色、字体等属性
 * https://blog.csdn.net/snowdream86/article/details/6776629
 * 2   https://blog.csdn.net/xijiaohuangcao/article/details/7839856
 * <p>
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

    private static final String LINE = "\n ----------------------------------------------------------------> \n";

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_textview;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_WIDGET_TEXTVIEW, true);
        //        FF34B3  C5C1AA
        tvWidget1.setTextColor(Color.parseColor("#A0522D"));
        tvWidget2.setTextColor(Color.parseColor("#EE7621"));
        tvWidget3.setTextColor(Color.parseColor("#FF4040"));
        tvWidget6.setTextColor(Color.parseColor("#EE6363"));

        //1 使用URL、Email、电话等显示超链接
        //1.1   TextView设置属性 android:autoLink="all"
        StringBuilder sb1 = new StringBuilder();
        sb1.append("个人主页：https://www.jooy.top \n")
                .append("我的邮箱：jooybao@foxmail.com \n")
                .append("我的电话： 10086").append(LINE);
        // 也可以通过动态代码实现
//        tvWidget1.setAutoLinkMask(Linkify.ALL);
        tvWidget1.setText(sb1.toString());
        //1.2 利用html标签实现超链接
        StringBuilder sb2 = new StringBuilder();
        sb2.append("<font color='red'>我的浏览器主页是：</font><br>  ")
                .append("<a href='https://www.jooy.top'>我的主页</a> <br>").append(LINE);
        Spanned spanned1 = Html.fromHtml(sb2.toString());
        tvWidget2.setText(spanned1);
        //注：必须设置上这句话才能跳转到浏览器
        tvWidget2.setMovementMethod(LinkMovementMethod.getInstance());
        //1.3 利用html标签插入图片   --  注意：图片不要加后缀类型 ic_launcher.png→ic_launcher
        StringBuilder sb3 = new StringBuilder();
        sb3.append("图片一： <img src='ic_launcher'/>")
                .append(" 图片二： <img src='ic_tabbar_home'/>")
                .append(" 图片三： <img src='ic_launcher_round'/>")
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
        String text = new String("点击【百度】开始搜索");
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(mActivity, "点击了", Toast.LENGTH_SHORT).show();
            }
        }, 3, 5, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 6, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#D4D6D8")), 6, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvWidget4.setText(spannableString);
        tvWidget4.setMovementMethod(LinkMovementMethod.getInstance());
        //1.5 实现跑马灯效果

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