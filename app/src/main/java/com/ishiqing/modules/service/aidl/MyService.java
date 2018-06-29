package com.ishiqing.modules.service.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * AIDL 服务端
 * <p>
 * https://ke.qq.com/webcourse/index.html#course_id=280524&term_id=100332242&taid=2081693339240396&vid=h142488do1z
 * <p>
 * 如何去访问Android系统或者其它App提供的Service服务？
 * 回顾不同程序之间交互的方式，前三种方式都是对外网客户端服务器模式：
 * 1.应用层 -- 采用HTTP协议；
 * 2.传输层 -- 采用Socket协议；
 * 3.WebService：基于HTTP协议中的SOAP协议，可以传输XML和JSON(JSON的话基于WEBSERVICE的Restful)；
 * 4.Android系统内部采用基于RPC协议的访问，此方式类似于Linux的管道技术，Android采用了AIDL技术来简化。
 * <p>
 * AIDL使用步骤：
 * 1. 对外提供的服务，除了继承Binder类之外，还需要实现我们抽取的接口（定义规范，面向接口编程）
 * 这里我们用 {@link IMyServiceProxy}
 * 2.
 * <p>
 * Created by javakam on 2018-6-29 Fri.
 */
public class MyService extends Service {
    /**
     * 定义一个内部类（Binder对象），完成对 MyService 类中方法的封装调用，并提供给外部进行使用
     */
    class MyServiceProxy extends Binder implements IMyServiceProxy {
        @Override
        public void playMusic(String name) {
            MyService.this.playMusic(name);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceProxy();
    }

    private void playMusic(String name) {

    }
}
