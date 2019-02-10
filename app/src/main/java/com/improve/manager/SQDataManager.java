package com.improve.manager;

import com.improve.UIRouter;
import com.improve.base.QDItemDescription;
import com.improve.modules.activity.ActiviyEntranceSwipeFragment;
import com.improve.modules.annotation.AnnotationActivity;
import com.improve.modules.eventbus3.EventBusMainActivity;
import com.improve.modules.greendao.GreenDaoM2MSwipeFragment;
import com.improve.modules.greendao.GreenDaoSwipeFragment;
import com.improve.modules.greendao.RxGreenDaoSwipeFragment;
import com.improve.modules.ipc.aidl.AIDIServiceSwipeFragment;
import com.improve.modules.ipc.socket.TCPClientActivity;
import com.improve.modules.network.HttpUrlConnectionSwipeFragment;
import com.improve.modules.network.增量更新;
import com.improve.modules.provider.ProviderFragment;
import com.improve.modules.rxjava.RxJavaFragment;
import com.improve.modules.service.ServiceSwipeFragment1;
import com.improve.modules.service.ServiceSwipeFragment2;
import com.improve.modules.service.ServiceSwipeFragment3;
import com.improve.modules.service.ServiceSwipeFragment4;
import com.improve.modules.service.intentservice.IntentServiceFragment;
import com.improve.modules.thread.ThreadNavFragment;
import com.improve.modules.ui_hencoder.DrawMainActivity;
import com.improve.modules.ui_process.art.activity.ArtSwipeFragment;
import com.improve.modules.ui_process.dragview.DragViewMainActivity;
import com.improve.modules.ui_process.event.ViewEventDispatchSwipeFragment;
import com.improve.modules.ui_process.heros.HeroSwipeFragment;
import com.improve.modules.ui_process.imageloader.ImageLoaderActivity;
import com.improve.modules.widgets.AutoCompleteTextViewFragment;
import com.improve.modules.widgets.CatFlexboxLayoutFragment;
import com.improve.modules.widgets.FlexboxLayoutFragment;
import com.improve.modules.widgets.TextViewFragment;
import com.improve.modules.widgets.smarttable.SmartTableSwipeFragment;
import com.improve.modules.widgets.v01优酷菜单.YouKuMenuFragment;
import com.improve.modules.widgets.v02广告轮播效果.BannerFragment;
import com.improve.modules.widgets.v03自定义下拉框.SpinnerFragment;
import com.improve.modules.window.WindowActivity;
import com.improve.utils.AppUtils;
import com.improve.utils.ResourceUtil;

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
        list.add(new QDItemDescription(ActiviyEntranceSwipeFragment.class, UIRouter.FRAG_ACTIVITY1, getImage()));
        list.add(new QDItemDescription(IntentServiceFragment.class, UIRouter.FRAG_INTENT_SERVICE, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment1.class, UIRouter.FRAG_SERVICE1, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment2.class, UIRouter.FRAG_SERVICE2, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment3.class, UIRouter.FRAG_SERVICE3, getImage()));
        list.add(new QDItemDescription(ServiceSwipeFragment4.class, UIRouter.FRAG_SERVICE4, getImage()));
        list.add(new QDItemDescription(ProviderFragment.class, UIRouter.FRAG_PROVIDER1, getImage()));
        list.add(new QDItemDescription(AIDIServiceSwipeFragment.class, UIRouter.FRAG_AIDL, getImage()));
        list.add(new QDItemDescription(TCPClientActivity.class, UIRouter.FRAG_TCP_CLIENT, getImage()));
        list.add(new QDItemDescription(AnnotationActivity.class, UIRouter.FRAG_ANNOTATION, getImage()));
        list.add(new QDItemDescription(ThreadNavFragment.class, UIRouter.FRAG_THREAD_NAV, getImage()));
        list.add(new QDItemDescription(HttpUrlConnectionSwipeFragment.class, UIRouter.FRAG_NETWORK, getImage()));
        return list;
    }

    public static List<QDItemDescription> getPopularDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
//        list.add(new QDItemDescription(WanSwipeFragment.class, UIRouter.FRAG_WAN_ANDROID, getImage()));
        list.add(new QDItemDescription(增量更新.class, UIRouter.FRAG_ZLGX, getImage()));
        list.add(new QDItemDescription(RxJavaFragment.class, UIRouter.FRAG_RXJAVA2, getImage()));
        list.add(new QDItemDescription(GreenDaoSwipeFragment.class, UIRouter.FRAG_GREENDAO, getImage()));
        list.add(new QDItemDescription(GreenDaoM2MSwipeFragment.class, UIRouter.FRAG_GREENDAO_M2M, getImage()));
        list.add(new QDItemDescription(RxGreenDaoSwipeFragment.class, UIRouter.FRAG_RX_GREENDAO, getImage()));
        list.add(new QDItemDescription(EventBusMainActivity.class, UIRouter.FRAG_EVENTBUS3, getImage()));
        return list;
    }

    public static List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        list.add(new QDItemDescription(ImageLoaderActivity.class, UIRouter.FRAG_UI_IMAGELOADER, getImage()));
        list.add(new QDItemDescription(TextViewFragment.class, UIRouter.FRAG_WIDGET_TEXTVIEW, getImage()));
        list.add(new QDItemDescription(AutoCompleteTextViewFragment.class, UIRouter.FRAG_WIDGET_EDITTEXT, getImage()));
        list.add(new QDItemDescription(FlexboxLayoutFragment.class, UIRouter.FRAG_WIDGET_FLEXBOX, getImage()));
        list.add(new QDItemDescription(CatFlexboxLayoutFragment.class, UIRouter.FRAG_WIDGET_FLEXBOX_CAT, getImage()));
        list.add(new QDItemDescription(DrawMainActivity.class, UIRouter.FRAG_UI_HENCODER, getImage()));
        list.add(new QDItemDescription(HeroSwipeFragment.class, UIRouter.FRAG_UI_HEROS, getImage()));
        list.add(new QDItemDescription(ViewEventDispatchSwipeFragment.class, UIRouter.FRAG_UI_EVENT_DISPATCH, getImage()));
        list.add(new QDItemDescription(DragViewMainActivity.class, UIRouter.FRAG_UI_DRAG, getImage()));
        list.add(new QDItemDescription(ArtSwipeFragment.class, UIRouter.FRAG_UI_ART, getImage()));
        list.add(new QDItemDescription(SmartTableSwipeFragment.class, UIRouter.FRAG_WIDGET_SMARTTABLE, getImage()));
        list.add(new QDItemDescription(WindowActivity.class, UIRouter.FRAG_WINDOW, getImage()));
        list.add(new QDItemDescription(YouKuMenuFragment.class, UIRouter.FRAG_YOUKUMENU, getImage()));
        list.add(new QDItemDescription(BannerFragment.class, UIRouter.FRAG_BANNER, getImage()));
        list.add(new QDItemDescription(SpinnerFragment.class, UIRouter.FRAG_SPINNER, getImage()));

        return list;
    }

}