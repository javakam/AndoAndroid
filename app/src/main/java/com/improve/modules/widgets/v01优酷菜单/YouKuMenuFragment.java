package com.improve.modules.widgets.v01优酷菜单;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.UiUtil;

/**
 * 01优酷菜单 {@link com.improve.library.utils.UiUtil hideView() }
 * Created by machangbao on 2018/10/1.
 */
public class YouKuMenuFragment extends BaseSwipeFragment {
    ImageView icon_home;
    ImageView icon_menu;
    RelativeLayout level2;
    RelativeLayout level3;

    private boolean isLevel2Show = true;
    private boolean isLevel3Show = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_v01_youku;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_YOUKUMENU, true);
        icon_home = v.findViewById(R.id.icon_home);
        icon_menu = v.findViewById(R.id.icon_menu);
        level2 = v.findViewById(R.id.level2);
        level3 = v.findViewById(R.id.level3);

        v.findViewById(R.id.icon_home).setOnClickListener(this);
        v.findViewById(R.id.icon_menu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
            default:
                break;
        }
    }
}
