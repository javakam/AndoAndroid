package com.ishiqing.manager;

import com.ishiqing.fragment.ServiceFragment;
import com.ishiqing.model.QDItemDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQDataManager {
    public static List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
           list.add(new QDItemDescription(ServiceFragment.class,"服务组件"));
        }
        return list;
    }
}
