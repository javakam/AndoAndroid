package com.ishiqing.modules.widgets;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import butterknife.BindView;

/**
 * FlexboxLayout
 * <p>
 * https://www.jianshu.com/p/9cc89e033a9f
 * <p>
 * https://github.com/google/flexbox-layout
 * <p>
 * FlexboxlayoutManager是支持View回收的，而FlexboxLayout是不支持View回收的，
 * FlexboxLayout 只适用于少量Item的场景，这也是为什么会出现FlexboxLayoutManager的原因吧
 *
 * @author javakam
 * @date 2018/7/6.
 */
public class CatFlexboxLayoutFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_flexbox;
    }

    @Override
    protected void initViews() {
//        initTopBar(UIRoute.FRAG_WIDGET_FLEXBOX, true);
        mActivity.setSupportActionBar(toolbar);
        toolbar.setTitle(UIRoute.FRAG_WIDGET_FLEXBOX_CAT);
        // 1
        FlexboxLayoutManager flexboxLayoutManager
                = new FlexboxLayoutManager(mActivity, FlexDirection.ROW, FlexWrap.WRAP);
        mRecyclerView.setLayoutManager(flexboxLayoutManager);
        // 2
        CatRvAdapter adapter = new CatRvAdapter();
        mRecyclerView.setAdapter(adapter);

    }


}
