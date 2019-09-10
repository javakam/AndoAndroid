package com.improve.common;

/**
 * Created by javakam on 2018/6/16.
 */
public class UpdateManager {
    private UpdateManager() {
    }

    private static final class SQUpdateManagerHolder {
        private static final UpdateManager INSTANCE = new UpdateManager();
    }

    public static UpdateManager getInstance() {
        return SQUpdateManagerHolder.INSTANCE;
    }
}
