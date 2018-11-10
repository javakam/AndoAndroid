package com.ishiqing.modules.widgets.v01优酷菜单;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.sq.library.utils.UiUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 01优酷菜单 {@link com.sq.library.utils.UiUtil hideView() }
 * Created by machangbao on 2018/10/1.
 */
public class YouKuMenuFragment extends BaseSwipeFragment {
    @BindView(R.id.icon_home)
    ImageView icon_home;
    @BindView(R.id.icon_menu)
    ImageView icon_menu;

    @BindView(R.id.level2)
    RelativeLayout level2;
    @BindView(R.id.level3)
    RelativeLayout level3;

    private boolean isLevel2Show = true;
    private boolean isLevel3Show = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_v01_youku;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRoute.FRAG_YOUKUMENU, true);
    }

    @OnClick({R.id.icon_home, R.id.icon_menu})
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.icon_home://home
                if (isLevel2Show) {
                    isLevel2Show = false;
                    if (isLevel3Show) {
                        isLevel3Show = false;
                        UiUtil.hideView(level2, 0);
                        UiUtil.hideView(level3, 200);
                    } else {
                        UiUtil.hideView(level2);
                    }
                } else {
                    isLevel2Show = true;
                    UiUtil.showView(level2);
                }
                break;
            case R.id.icon_menu://菜单
                if (isLevel3Show) {
                    isLevel3Show = false;
                    UiUtil.hideView(level3);
                } else {
                    isLevel3Show = true;
                    UiUtil.showView(level3);
                }
                break;
        }
    }
}
