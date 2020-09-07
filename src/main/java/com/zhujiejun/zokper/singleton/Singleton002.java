package com.zhujiejun.zokper.singleton;

public final class Singleton002 {
    private byte[] data = new byte[1024];
    private static Singleton002 instance = null;

    private Singleton002() {

    }

    public static synchronized Singleton002 getInstance() {
        if (null == instance) {
            instance = new Singleton002();
        }
        return instance;
    }
}
