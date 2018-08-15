package com.ishiqing.modules.widgets;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.fragment.BaseSwipeFragment;

import butterknife.BindView;

/**
 * RecyclerView + FlexboxLayoutManager
 * <p>
 * {@link FlexboxLayoutSwipeFragment}
 */
public class CatFlexboxLayoutSwipeFragment extends BaseSwipeFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_flexbox_cat;
    }

    @Override
    protected void initViews(View v) {
//        initTopBar(UIRoute.FRAG_WIDGET_FLEXBOX, true);
        mActivity.setSupportActionBar(toolbar);
        toolbar.setTitle(UIRoute.FRAG_WIDGET_FLEXBOX_CAT);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        // 1
        FlexboxLayoutManager manager
                = new FlexboxLayoutManager(mActivity, FlexDirection.ROW, FlexWrap.WRAP);
        // or
        //设置主轴排列方式
        /*manager.setFlexDirection(FlexDirection.ROW);*/
        //设置是否换行
        /*manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);*/
        mRecyclerView.setLayoutManager(manager);

        // 2
        CatRvAdapter adapter = new CatRvAdapter();
        mRecyclerView.setAdapter(adapter);

    }


}
