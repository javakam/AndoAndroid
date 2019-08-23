package com.improve.modules.annotation.base;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * Useage : ViewInjectUtils.inject(Ativity);
 */
public class ViewInjectUtils {
    private static final String TAG = "123";

    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    public static void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
        injectEvents(activity);//动态代理实现
//        injectEvents2(activity);
    }

    /**
     * 注入主布局文件
     *
     * @param activity
     */
    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // 查询类上是否存在ContentView注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null)// 存在
        {
            int contentViewLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW, int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewLayoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入所有的控件
     *
     * @param activity
     */
    private static void injectViews(Activity activity) {
        /*
         通过字节码获取activity类中所有的字段，在获取Field的时候一定要使用
         getDeclaredFields(),因为只有该方法才能获取到任何权限修饰的Filed，包括私有的。
         */
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // 遍历所有成员变量
        for (Field field : fields) {
            //获取到字段上面的注解对象
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            //一定对annotation是否等于null进行判断，因为并不是所有Filed上都有我们想要的注解
            if (viewInjectAnnotation != null) {
                //获取注解中的值
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1) {
                    Log.e(TAG, viewId + "");
                    // 初始化View
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        Object resView = method.invoke(activity, viewId);//获取控件
                        //设置为可访问，暴力反射，就算是私有的也能访问到
                        field.setAccessible(true);
                        field.set(activity, resView);//将该控件设置给field对象
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 注入所有的事件
     *
     * @param activity
     */
    private static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        //遍历所有的方法
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            //拿到方法上的所有的注解
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //拿到注解上的注解
                EventBase eventBaseAnnotation = annotationType.getAnnotation(EventBase.class);
                //如果设置为EventBase
                if (eventBaseAnnotation != null) {
                    //取出设置监听器的名称，监听器的类型，调用的方法名
                    String listenerSetter = eventBaseAnnotation.listenerSetter();
                    Class<?> listenerType = eventBaseAnnotation.listenerType();
                    String methodName = eventBaseAnnotation.methodName();
                    try {
                        //拿到Onclick注解中的value方法
                        Method aMethod = annotationType.getDeclaredMethod("value");
                        //取出所有的viewId
                        int[] viewIds = (int[]) aMethod.invoke(annotation, new Object());//null
                        //通过InvocationHandler设置代理
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(
                                listenerType.getClassLoader(),
                                new Class<?>[]{listenerType}, handler);
                        //遍历所有的View，设置事件
                        for (int viewId : viewIds) {
                            View view = activity.findViewById(viewId);
                            Method setEventListenerMethod = view.getClass()
                                    .getMethod(listenerSetter, listenerType);
                            setEventListenerMethod.invoke(view, listener);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void injectEvents2(final Activity activity) {
        Class clazz = activity.getClass();
        //获取所有的方法（私有方法也可以获取到）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        int length = declaredMethods.length;
        for (int i = 0; i < length; i++) {
            final Method method = declaredMethods[i];
            //获取方法上面的注解
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation != null) {//如果该方法上没有注解，执行下一个循环
                //获取注解中的数据，因为可以给多个button绑定点击事件，因此定义注解类时使用的是int[]作为数据类型。
                int[] values = annotation.value();
                for (int val : values) {
                    try {
                        Method methodFindView = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        View v = (View) methodFindView.invoke(activity, val);
                        v.setOnClickListener(v1 -> {
                            try {
                                //反射调用用户指定的方法
                                method.invoke(activity, v);//v1
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class DynamicHandler implements InvocationHandler {
        private WeakReference<Object> handlerRef;
        private final HashMap<String, Method> methodMap = new HashMap<String, Method>(
                1);

        public DynamicHandler(Object handler) {
            this.handlerRef = new WeakReference<Object>(handler);
        }

        public void addMethod(String name, Method method) {
            methodMap.put(name, method);
        }

        public Object getHandler() {
            return handlerRef.get();
        }

        public void setHandler(Object handler) {
            this.handlerRef = new WeakReference<Object>(handler);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Object handler = handlerRef.get();
            if (handler != null) {
                String methodName = method.getName();
                method = methodMap.get(methodName);
                if (method != null) {
                    return method.invoke(handler, args);
                }
            }
            return null;
        }
    }

}