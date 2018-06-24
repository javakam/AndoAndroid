package com.ishiqing.modules.widgets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 使用 SpannableString + ImageSpan 为 EditText 添加图片
 * <p>
 * Created by javakam on 2018/6/19.
 */
public class AutoCompleteTextViewFragment extends BaseFragment {
    @BindView(R.id.actv1)
    AutoCompleteTextView actvImage;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_auto_complete_textview;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_WIDGET_AUTOCOMPLETE_TEXTVIEW, true);
    }

    @OnClick({R.id.btAddImage1, R.id.btAddImage2, R.id.btAddImage3})
    void addImageToAcTextView(View v) {
        // 必须先有个  SpannableString !!!
        SpannableString spStr = new SpannableString("用于显示图片的SpannableString，这里写什么都行");
        // 做个 ImageSpan 放到 SpannableString 上
        ImageSpan imageSpan = null;
        switch (v.getId()) {
            case R.id.btAddImage1:
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                imageSpan = new ImageSpan(bitmap1);
                spStr.setSpan(imageSpan, 0, spStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.btAddImage2:
                Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_tabbar_home);
                imageSpan = new ImageSpan(bitmap2);
                spStr.setSpan(imageSpan, 0, spStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.btAddImage3:
                Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_tabbar_component);
                imageSpan = new ImageSpan(bitmap3);
                spStr.setSpan(imageSpan, 0, spStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            default:
                break;
        }
        // 注意： 这里要用控件本身去拼接上
        actvImage.append(spStr);
    }

}
