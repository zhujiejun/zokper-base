package com.zhujiejun.zokper.singleton;

public final class Singleton001 {
    private byte[] data = new byte[1024];
    private static Singleton001 instance = null;

    private Singleton001() {

    }

    public static Singleton001 getInstance() {
        if (null == instance) {
            instance = new Singleton001();
        }
        return instance;
    }
}
