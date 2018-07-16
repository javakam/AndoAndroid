package com.ishiqing.modules.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Android {@see 注解开发.md}
 * <p>
 * Created by javakam on 2018/7/16 0016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ViewInject {
    public abstract int value() default -1;
}
