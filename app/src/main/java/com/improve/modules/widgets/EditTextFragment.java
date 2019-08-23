package com.improve.modules.widgets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * 使用 SpannableString + ImageSpan 为 EditText 添加图片
 * <p>
 *
 * @author javakam
 * @date 2018/6/19
 */
public class EditTextFragment extends BaseSwipeFragment {
    AutoCompleteTextView actvImage;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_edittext;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_WIDGET_EDITTEXT, true);
        actvImage = v.findViewById(R.id.actv1);

        v.findViewById(R.id.btAddImage1).setOnClickListener(this);
        v.findViewById(R.id.btAddImage2).setOnClickListener(this);
        v.findViewById(R.id.btAddImage3).setOnClickListener(this);
        v.findViewById(R.id.btq_show_actv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 必须先有个  SpannableString !!!
        SpannableString spStr = new SpannableString("用于显示图片的SpannableString，这里写什么都行");
        // 做个 ImageSpan 放到 SpannableString 上
        ImageSpan imageSpan = null;
        switch (v.getId()) {
            case R.id.btAddImage1:
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                imageSpan = new ImageSpan(bitmap1);
                spStr.setSpan(imageSpan, 0, spStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                // 注意： 这里要用控件本身去拼接上
                actvImage.append(spStr);
                break;
            case R.id.btAddImage2:
                Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_tabbar_home);
                imageSpan = new ImageSpan(bitmap2);
                spStr.setSpan(imageSpan, 0, spStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                // 注意： 这里要用控件本身去拼接上
                actvImage.append(spStr);
                break;
            case R.id.btAddImage3:
                Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_tabbar_component);
                imageSpan = new ImageSpan(bitmap3);
                spStr.setSpan(imageSpan, 0, spStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                // 注意： 这里要用控件本身去拼接上
                actvImage.append(spStr);
                break;
            case R.id.btq_show_actv:
                startFragment(new AutoCompleteTextViewFragment());
                break;
            default:
                break;
        }
    }

}