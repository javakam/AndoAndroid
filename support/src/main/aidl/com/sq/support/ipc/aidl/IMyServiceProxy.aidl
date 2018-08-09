package com.sq.support.ipc.aidl;

/**
 * 此接口定义对外提供的方法  【注】aidl文件中的public修饰符删掉
 * <p>
 * 思想：定义规范，面向接口编程
 * <p>
 * Created by javakam on 2018/6/29 0029.
 */
interface IMyServiceProxy {
    void playMusic(String name);
}
