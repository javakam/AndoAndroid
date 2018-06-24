package com.ishiqing.manager;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.bean.QDItemDescription;
import com.ishiqing.modules.activity.ActiviyEntranceFragment;
import com.ishiqing.modules.provider.ProviderFragment1;
import com.ishiqing.modules.service.ServiceFragment1;
import com.ishiqing.modules.service.ServiceFragment2;
import com.ishiqing.modules.service.ServiceFragment3;
import com.ishiqing.modules.service.ServiceFragment4;
import com.ishiqing.modules.widgets.ButtonFragment;
import com.ishiqing.modules.widgets.AutoCompleteTextViewFragment;
import com.ishiqing.modules.widgets.TextViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQDataManager {
    public static List<QDItemDescription> getHomeGridDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(TextViewFragment.class, UIRoute.FRAG_WIDGET_TEXTVIEW, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(AutoCompleteTextViewFragment.class, UIRoute.FRAG_WIDGET_AUTOCOMPLETE_TEXTVIEW, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ButtonFragment.class, UIRoute.FRAG_WIDGET_BUTTON, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ActiviyEntranceFragment.class, UIRoute.FRAG_ACTIVITY1, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment1.class, UIRoute.FRAG_SERVICE1, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment2.class, UIRoute.FRAG_SERVICE2, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment3.class, UIRoute.FRAG_SERVICE3, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment4.class, UIRoute.FRAG_SERVICE4, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ProviderFragment1.class, UIRoute.FRAG_PROVIDER1, R.mipmap.icon_grid_layout));
        return list;
    }

    public static List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(TextViewFragment.class, UIRoute.FRAG_WIDGET_TEXTVIEW, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(AutoCompleteTextViewFragment.class, UIRoute.FRAG_WIDGET_AUTOCOMPLETE_TEXTVIEW, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ButtonFragment.class, UIRoute.FRAG_WIDGET_BUTTON, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ActiviyEntranceFragment.class, UIRoute.FRAG_ACTIVITY1, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment1.class, UIRoute.FRAG_SERVICE1, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment2.class, UIRoute.FRAG_SERVICE2, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment3.class, UIRoute.FRAG_SERVICE3, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment4.class, UIRoute.FRAG_SERVICE4, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ProviderFragment1.class, UIRoute.FRAG_PROVIDER1, R.mipmap.icon_grid_layout));
        for (int i = 0; i < 10; i++) {
            list.add(new QDItemDescription(ServiceFragment1.class, UIRoute.FRAG_OTHER, R.mipmap.icon_grid_layout));
        }
        return list;
    }

}
