package com.android.example.log;

/**
 * @author:無忌
 * @date:2020/11/4
 * @description:
 */
public final class Logger {

    public enum Type {

        DEFAULT(0, "default"),
        ANDROID(1, "android");

        private int type;
        private String name;

        Type(int type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    private static Type mType;

    private Logger() {

    }

    public static void init(Type type) {
        mType = type;
    }

    public static ILogger get() {
        mType = mType == null ? Type.DEFAULT : mType;
        switch (mType) {
            case DEFAULT:
                return DefaultLogger.getInstance();
            default:
                return AndroidLogger.getInstance();
        }
    }

    public static ILogger get(Type type) {
        type = type == null ? Type.DEFAULT : type;
        switch (type) {
            case DEFAULT:
                return DefaultLogger.getInstance();
            default:
                return AndroidLogger.getInstance();
        }
    }
}
