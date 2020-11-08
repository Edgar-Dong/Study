package com.android.example.framework.binder.customsize;

import android.os.IInterface;

/**
 * @author:無忌
 * @date:2020/10/21
 * @description:
 */
public interface IMyInterface extends IInterface {
    void setCustomName(String name);
    String getCustomName();
}
