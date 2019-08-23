package com.improve.common;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQUpdateManager {
    private SQUpdateManager() {
    }

    private static final class SQUpdateManagerHolder {
        private static final SQUpdateManager INSTANCE = new SQUpdateManager();
    }

    public static SQUpdateManager getInstance() {
        return SQUpdateManagerHolder.INSTANCE;
    }
}
