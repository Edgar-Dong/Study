package com.android.example.thirdlib.dagger;

import com.android.example.common.log.Logger;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
public class ClazzB {
    @Inject
    public ClazzB() {
    }

    public void methodB() {
        Logger.get().d("ClazzB", "methodB");
    }
}
