package com.ishiqing.manager;

import com.ishiqing.UIRoute;
import com.ishiqing.base.QDItemDescription;
import com.ishiqing.modules.activity.ActiviyEntranceSwipeFragment;
import com.ishiqing.modules.annotation.AnnotationActivity;
import com.ishiqing.modules.eventbus3.EventBusMainActivity;
import com.ishiqing.modules.greendao.GreenDaoM2MSwipeFragment;
import com.ishiqing.modules.greendao.GreenDaoSwipeFragment;
import com.ishiqing.modules.greendao.RxGreenDaoSwipeFragment;
import com.ishiqing.modules.ipc.aidl.AIDIServiceSwipeFragment;
import com.ishiqing.modules.ipc.socket.TCPClientActivity;
import com.ishiqing.modules.network.HttpUrlConnectionSwipeFragment;
import com.ishiqing.modules.network.增量更新;
import com.ishiqing.modules.provider.ProviderSwipeFragment1;
import com.ishiqing.modules.rxjava.RxJavaSwipeFragment1;
import com.ishiqing.modules.service.ServiceSwipeFragment1;
import com.ishiqing.modules.service.ServiceSwipeFragment2;
import com.ishiqing.modules.service.ServiceSwipeFragment3;
import com.ishiqing.modules.service.ServiceSwipeFragment4;
import com.ishiqing.modules.service.intentservice.IntentServiceSwipeFragment;
import com.ishiqing.modules.thread.ThreadNavSwipeFragment;
import com.ishiqing.modules.ui_hencoder.DrawMainActivity;
import com.ishiqing.modules.ui_process.art.c3.activity.ArtSwipeFragment;
import com.ishiqing.modules.ui_process.dragview.DragViewMainActivity;
import com.ishiqing.modules.ui_process.event.ViewEventDispatchSwipeFragment;
import com.ishiqing.modules.ui_process.heros.HeroSwipeFragment;
import com.ishiqing.modules.ui_process.imageloader.ImageLoaderActivity;
import com.ishiqing.modules.wanandroid.WanSwipeFragment;
import com.ishiqing.modules.widgets.AutoCompleteTextViewSwipeFragment;
import com.ishiqing.modules.widgets.ButtonSwipeFragment;
import com.ishiqing.modules.widgets.CatFlexboxLayoutSwipeFragment;
import com.ishiqing.modules.widgets.FlexboxLayoutSwipeFragment;
import com.ishiqing.modules.widgets.TextViewSwipeFragment;
import com.ishiqing.modules.widgets.smarttable.SmartTableSwipeFragment;
import com.ishiqing.modules.widgets.v01优酷菜单.YouKuMenuFragment;
import com.ishiqing.modules.widgets.v02广告轮播效果.BannerFragment;
import com.ishiqing.modules.widgets.v03自定义下拉框.SpinnerFragment;
import com.ishiqing.modules.window.WindowActivity;
import com.sq.library.utils.AppUtils;
import com.sq.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQDataManager {
    private static final String IMAGE_PRE_FIX = "ic_style_";
    private static final int IMAGE_COUNT = 12;

    private static int getImage() {
        // java求1到10的随机数问题
        // Math.random() * 10 默认为左闭右开区间即[0,9)，所以还需要在最后面+1才能是[1,10]
        Double i = Math.random() * IMAGE_COUNT + 1;
//        L.i("i:" + i);
        return ResourceUtil.getMipmapId(AppUtils.getContext(), IMAGE_PRE_FIX + i.intValue());
    }

    public static List<QDItemDescription> getHomeGridDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(ActiviyEntranceSwipeFragment.class, UIRoute.FRAG_ACTIVITY1, getImage()));
        list.add(new QDItemDescription(IntentServiceSwipeFragment.class, UIRoute.FRAG_INTENT_SERVICE, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment1.class, UIRoute.FRAG_SERVICE1, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment2.class, UIRoute.FRAG_SERVICE2, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment3.class, UIRoute.FRAG_SERVICE3, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment4.class, UIRoute.FRAG_SERVICE4, getImage()));
        list.add(new QDItemDescription(ProviderSwipeFragment1.class, UIRoute.FRAG_PROVIDER1, getImage()));
        list.add(new QDItemDescription(AIDIServiceSwipeFragment.class, UIRoute.FRAG_AIDL, getImage()));
        list.add(new QDItemDescription(TCPClientActivity.class, UIRoute.FRAG_TCP_CLIENT, getImage()));
        list.add(new QDItemDescription(AnnotationActivity.class, UIRoute.FRAG_ANNOTATION, getImage()));
        list.add(new QDItemDescription(ThreadNavSwipeFragment.class, UIRoute.FRAG_THREAD_NAV, getImage()));
        list.add(new QDItemDescription(HttpUrlConnectionSwipeFragment.class, UIRoute.FRAG_NETWORK, getImage()));
        return list;
    }

    public static List<QDItemDescription> getPopularDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(WanSwipeFragment.class, UIRoute.FRAG_WAN_ANDROID, getImage()));
        list.add(new QDItemDescription(增量更新.class, UIRoute.FRAG_ZLGX, getImage()));
        list.add(new QDItemDescription(RxJavaSwipeFragment1.class, UIRoute.FRAG_RXJAVA2, getImage()));
        list.add(new QDItemDescription(GreenDaoSwipeFragment.class, UIRoute.FRAG_GREENDAO, getImage()));
        list.add(new QDItemDescription(GreenDaoM2MSwipeFragment.class, UIRoute.FRAG_GREENDAO_M2M, getImage()));
        list.add(new QDItemDescription(RxGreenDaoSwipeFragment.class, UIRoute.FRAG_RX_GREENDAO, getImage()));
        list.add(new QDItemDescription(EventBusMainActivity.class, UIRoute.FRAG_EVENTBUS3, getImage()));
        return list;
    }

    public static List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(ImageLoaderActivity.class, UIRoute.FRAG_UI_IMAGELOADER, getImage()));
        list.add(new QDItemDescription(TextViewSwipeFragment.class, UIRoute.FRAG_WIDGET_TEXTVIEW, getImage()));
        list.add(new QDItemDescription(AutoCompleteTextViewSwipeFragment.class, UIRoute.FRAG_WIDGET_EDITTEXT, getImage()));
        list.add(new QDItemDescription(ButtonSwipeFragment.class, UIRoute.FRAG_WIDGET_BUTTON, getImage()));
        list.add(new QDItemDescription(FlexboxLayoutSwipeFragment.class, UIRoute.FRAG_WIDGET_FLEXBOX, getImage()));
        list.add(new QDItemDescription(CatFlexboxLayoutSwipeFragment.class, UIRoute.FRAG_WIDGET_FLEXBOX_CAT, getImage()));
        list.add(new QDItemDescription(DrawMainActivity.class, UIRoute.FRAG_UI_HENCODER, getImage()));
        list.add(new QDItemDescription(HeroSwipeFragment.class, UIRoute.FRAG_UI_HEROS, getImage()));
        list.add(new QDItemDescription(ViewEventDispatchSwipeFragment.class, UIRoute.FRAG_UI_EVENT_DISPATCH, getImage()));
        list.add(new QDItemDescription(DragViewMainActivity.class, UIRoute.FRAG_UI_DRAG, getImage()));
        list.add(new QDItemDescription(ArtSwipeFragment.class, UIRoute.FRAG_UI_ART, getImage()));
        list.add(new QDItemDescription(SmartTableSwipeFragment.class, UIRoute.FRAG_WIDGET_SMARTTABLE, getImage()));
        list.add(new QDItemDescription(WindowActivity.class, UIRoute.FRAG_WINDOW, getImage()));
        list.add(new QDItemDescription(YouKuMenuFragment.class, UIRoute.FRAG_YOUKUMENU, getImage()));
        list.add(new QDItemDescription(BannerFragment.class, UIRoute.FRAG_BANNER, getImage()));
        list.add(new QDItemDescription(SpinnerFragment.class, UIRoute.FRAG_SPINNER, getImage()));

        return list;
    }

}
