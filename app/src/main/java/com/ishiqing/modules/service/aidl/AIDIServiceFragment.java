package com.ishiqing.modules.service.aidl;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

/**
 * AIDL（Android interface defination language）通信技术
 * <p>
 * https://ke.qq.com/webcourse/index.html#course_id=280524&term_id=100332242&taid=2081693339240396&vid=h142488do1z
 * <p>
 * 如何去访问Android系统或者其它App提供的Service服务？
 * 回顾不同程序之间交互的方式，前3种方式都是对外网客户端服务器模式：
 * 1.应用层 客户端--》服务器采用的HTTP协议；
 * 2.传输层 客户端--》服务器采用Socket协议；
 * 3.WebService：基于HTTP协议中的SOAP协议，可以传输XML和JSON；
 * 4.Android系统内部采用基于RPC协议的访问，此方式类似于Linux的管道技术，Android采用了AIDL技术来简化。
 * <p>
 * AIDL使用步骤：
 * 1.服务端的Service服务除了继承Service类之外还需要抽取接口（定义规范，面向接口编程）
 * <p>
 * Created by javakam on 2018/6/25 0025.
 */
public class AIDIServiceFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_aidl;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_AIDL, true);
    }
}
