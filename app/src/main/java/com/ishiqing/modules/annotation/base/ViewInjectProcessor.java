package com.ishiqing.modules.annotation.base;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Annotation Processor
 * <p>
 * 参考：
 * 鸿洋 Android 打造编译时注解解析框架  https://blog.csdn.net/lmj623565791/article/details/43452969
 * <p>
 * Annotation实战【自定义AbstractProcessor】  http://www.cnblogs.com/avenwu/p/4173899.html
 * <p>
 * Created by javakam on 2018/9/4.
 */
public class ViewInjectProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
