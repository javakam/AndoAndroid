package com.ishiqing.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.modules.activity.SampleActivity1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by javakam on 2018/6/20.
 */
public class BasePagerAdapter extends PagerAdapter {
    private Context context;
    private Map<ContentPage, View> mPageMap = new HashMap<>();

    public BasePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ContentPage.SIZE;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ContentPage page = ContentPage.getPage(position);
        View view = getPageView(page);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.addView(view, params);
        return view;
    }

    private View getPageView(ContentPage page) {
        View view = mPageMap.get(page);
        if (view == null) {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            textView.setTextColor(ContextCompat.getColor(context, R.color.app_color_description));
            textView.setText("这是第 " + (page.getPosition() + 1) + " 个 Item 的内容区");
            if (page.getPosition() == 0) {
                textView.setOnClickListener(v ->
                        context.startActivity(new Intent(context, SampleActivity1.class)));
            }
            view = textView;
            mPageMap.put(page, view);
        }
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
