package com.improve.modules.ui_hencoder;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.improve.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * https://github.com/javakam/Aimprove/tree/master/sqdraw
 */
public class PracticeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_draw_pie_chart, R.layout.practice_pie_chart));
        pageModels.add(new PageModel(R.string.title_draw_histogram, R.layout.practice_histogram));

        pageModels.add(new PageModel(R.string.title_draw_color, R.layout.practice_color));
        pageModels.add(new PageModel(R.string.title_draw_circle, R.layout.practice_circle));
//        pageModels.add(new PageModel(R.string.title_draw_rect, R.layout.practice_rect));
//        pageModels.add(new PageModel(R.string.title_draw_point, R.layout.practice_point));
        pageModels.add(new PageModel(R.string.title_draw_oval, R.layout.practice_oval));
//        pageModels.add(new PageModel(R.string.title_draw_line, R.layout.practice_line));
//        pageModels.add(new PageModel(R.string.title_draw_round_rect, R.layout.practice_round_rect));
        pageModels.add(new PageModel(R.string.title_draw_arc, R.layout.practice_arc));
//        pageModels.add(new PageModel(R.string.title_draw_path, R.layout.practice_path));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        tabLayout = findViewById(R.id.pcTabLayout);
        viewPager = findViewById(R.id.pcViewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(pageModels.get(position).practiceLayoutRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });
        viewPager.setOffscreenPageLimit(5);
        //设置TabLayout的样式
        tabLayout.setTabMode(MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class PageModel {
        @StringRes
        int titleRes;
        @LayoutRes
        int practiceLayoutRes;

        PageModel(@StringRes int titleRes, @LayoutRes int practiceLayoutRes) {
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }
}
