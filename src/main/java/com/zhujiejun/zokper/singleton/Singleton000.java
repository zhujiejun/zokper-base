package com.zhujiejun.zokper.singleton;

//
public final class Singleton000 {
    private byte[] data = new byte[1024];
    private static Singleton000 instance = new Singleton000();

    private Singleton000() {

    }

    public static Singleton000 getInstance() {
        return instance;
    }
}
