package com.ishiqing.manager;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.bean.QDItemDescription;
import com.ishiqing.modules.ServiceFragment1;
import com.ishiqing.modules.ServiceFragment2;
import com.ishiqing.modules.ServiceFragment3;
import com.ishiqing.modules.ServiceFragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQDataManager {

    public static List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(ServiceFragment1.class, UIRoute.FRAG_SERVICE1, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment2.class, UIRoute.FRAG_SERVICE2, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment3.class, UIRoute.FRAG_SERVICE3, R.mipmap.icon_grid_layout));
        list.add(new QDItemDescription(ServiceFragment4.class, UIRoute.FRAG_SERVICE4, R.mipmap.icon_grid_layout));
        for (int i = 0; i < 20; i++) {
            list.add(new QDItemDescription(ServiceFragment1.class, UIRoute.FRAG_OTHER, R.mipmap.icon_grid_layout));
        }
        return list;
    }

}
