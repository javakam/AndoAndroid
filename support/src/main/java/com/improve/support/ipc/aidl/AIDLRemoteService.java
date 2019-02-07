package com.improve.support.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * AIDL 客户端
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
 * 1. 对外提供的服务，除了继承Binder类之外，还需要实现我们抽取的接口（即定义规范，面向接口编程）
 * 这里我们用 {@see IMyServiceProxy}
 * 2. 把接口后缀名修改为 aidl ，系统会自动生成相应的标准文件，把 aidl文件中的public修饰符删掉！！【注】
 * 3. 对外提供的服务类，MyServiceProxy 继承 Stub
 * 4. 在服务端APP的清单文件中配置Service隐式调用的代码
 * 5. 把服务端的aidl文件与包名都拷贝到client端（app） - 客户端自身也会生成.java接口，由于是同一个aidl文件生成的，
 * 因此客户端和服务端有相同的接口
 * 6. 客户端需要用隐式的方式去绑定服务，返回的时候通过 IMyServiceProxy 接收
 * 7. 测试时先启动测试app，再启动client
 * <p>
 * Created by javakam on 2018-6-29 Fri.
 */
public class AIDLRemoteService extends Service {
    /**
     * 定义一个内部类（Binder对象），完成对 AIDLRemoteService 类中方法的封装调用，并提供给外部进行使用
     */
    class MyServiceProxy extends IMyServiceProxy.Stub {
        @Override
        public void playMusic(String name) throws RemoteException {
            Log.d("123", "AIDL远程服务方法...");
            AIDLRemoteService.this.playMusic(name);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("123", "AIDL RemoteService onBind");
        return new MyServiceProxy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
//        return super.onUnbind(intent);
        Log.d("123", "AIDL RemoteService onUnbind");
        return true;
    }

    private void playMusic(String name) {
        Log.e("123", "playing music : " + name);
    }
}
