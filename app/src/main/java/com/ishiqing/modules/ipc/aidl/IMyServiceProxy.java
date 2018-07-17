package com.ishiqing.modules.ipc.aidl;

/**
 * 此接口定义对外提供的方法
 * <p>
 * 思想：定义规范，面向接口编程
 * <p>
 * Created by javakam on 2018/6/29 0029.
 */
public interface IMyServiceProxy {
    void playMusic(String name);
}
