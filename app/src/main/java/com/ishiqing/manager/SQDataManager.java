package com.ishiqing.manager;

import com.ishiqing.UIRoute;
import com.ishiqing.base.QDItemDescription;
import com.ishiqing.modules.activity.ActiviyEntranceFragment;
import com.ishiqing.modules.annotation.AnnotationFragment;
import com.ishiqing.modules.greendao.GreenDaoFragment;
import com.ishiqing.modules.greendao.GreenDaoM2MFragment;
import com.ishiqing.modules.greendao.RxGreenDaoFragment;
import com.ishiqing.modules.network.HttpUrlConnectionFragment;
import com.ishiqing.modules.network.增量更新;
import com.ishiqing.modules.provider.ProviderFragment1;
import com.ishiqing.modules.rxjava.RxJavaFragment1;
import com.ishiqing.modules.service.IntentServiceFragment;
import com.ishiqing.modules.service.ServiceFragment1;
import com.ishiqing.modules.service.ServiceFragment2;
import com.ishiqing.modules.service.ServiceFragment3;
import com.ishiqing.modules.service.ServiceFragment4;
import com.ishiqing.modules.service.aidl.AIDIServiceFragment;
import com.ishiqing.modules.thread.ThreadNavFragment;
import com.ishiqing.modules.wanandroid.WanFragment;
import com.ishiqing.modules.widgets.AutoCompleteTextViewFragment;
import com.ishiqing.modules.widgets.ButtonFragment;
import com.ishiqing.modules.widgets.CatFlexboxLayoutFragment;
import com.ishiqing.modules.widgets.FlexboxLayoutFragment;
import com.ishiqing.modules.widgets.TextViewFragment;
import com.sq.library.utils.AppUtils;
import com.sq.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQDataManager {
    private static final String IMAGE_PRE_FIX = "ic_style_";

    private static int getImage() {
        // java求1到10的随机数问题
        // Math.random() * 10 默认为左闭右开区间即[0,9)，所以还需要在最后面+1才能是[1,10]
        Double i = Math.random() * 32 + 1;
//        L.i("i:" + i);
        return ResourceUtil.getMipmapId(AppUtils.getContext(), IMAGE_PRE_FIX + i.intValue());
    }

    public static List<QDItemDescription> getHomeGridDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(ActiviyEntranceFragment.class, UIRoute.FRAG_ACTIVITY1, getImage()));
        list.add(new QDItemDescription(IntentServiceFragment.class, UIRoute.FRAG_INTENT_SERVICE, getImage()));
        list.add(new QDItemDescription(ServiceFragment1.class, UIRoute.FRAG_SERVICE1, getImage()));
        list.add(new QDItemDescription(ServiceFragment2.class, UIRoute.FRAG_SERVICE2, getImage()));
        list.add(new QDItemDescription(ServiceFragment3.class, UIRoute.FRAG_SERVICE3, getImage()));
        list.add(new QDItemDescription(ServiceFragment4.class, UIRoute.FRAG_SERVICE4, getImage()));
        list.add(new QDItemDescription(ProviderFragment1.class, UIRoute.FRAG_PROVIDER1, getImage()));
        list.add(new QDItemDescription(AnnotationFragment.class, UIRoute.FRAG_ANNOTATION, getImage()));
        list.add(new QDItemDescription(AIDIServiceFragment.class, UIRoute.FRAG_AIDL, getImage()));
        list.add(new QDItemDescription(ThreadNavFragment.class, UIRoute.FRAG_THREAD_NAV, getImage()));
        list.add(new QDItemDescription(HttpUrlConnectionFragment.class, UIRoute.FRAG_NETWORK, getImage()));
        list.add(new QDItemDescription(WanFragment.class, UIRoute.FRAG_WAN_ANDROID, getImage()));
        list.add(new QDItemDescription(增量更新.class, UIRoute.FRAG_ZLGX, getImage()));
        list.add(new QDItemDescription(RxJavaFragment1.class, UIRoute.FRAG_RXJAVA2, getImage()));
        list.add(new QDItemDescription(GreenDaoFragment.class, UIRoute.FRAG_GREENDAO, getImage()));
        list.add(new QDItemDescription(GreenDaoM2MFragment.class, UIRoute.FRAG_GREENDAO_M2M, getImage()));
        list.add(new QDItemDescription(RxGreenDaoFragment.class, UIRoute.FRAG_RX_GREENDAO, getImage()));
        return list;
    }

    public static List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(TextViewFragment.class, UIRoute.FRAG_WIDGET_TEXTVIEW, getImage()));
        list.add(new QDItemDescription(AutoCompleteTextViewFragment.class, UIRoute.FRAG_WIDGET_EDITTEXT, getImage()));
        list.add(new QDItemDescription(ButtonFragment.class, UIRoute.FRAG_WIDGET_BUTTON, getImage()));
        list.add(new QDItemDescription(FlexboxLayoutFragment.class, UIRoute.FRAG_WIDGET_FLEXBOX, getImage()));
        list.add(new QDItemDescription(CatFlexboxLayoutFragment.class, UIRoute.FRAG_WIDGET_FLEXBOX_CAT, getImage()));
        return list;
    }

}
