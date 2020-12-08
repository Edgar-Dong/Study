package com.android.component_module2.thirdlib.dagger;

import com.android.example.common.log.Logger;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
public class ClazzA {
    @Inject
    public ClazzA() {
    }

    public void methodA() {
        Logger.get().d("ClazzA", "methodA");
    }
}
