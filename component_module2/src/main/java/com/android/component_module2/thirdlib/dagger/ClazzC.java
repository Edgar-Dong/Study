package com.android.component_module2.thirdlib.dagger;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
public class ClazzC {
    private ClazzA clazzA;
    private ClazzB clazzB;

    @Inject
    public ClazzC(ClazzA clazzA, ClazzB clazzB) {
        this.clazzA = clazzA;
        this.clazzB = clazzB;
    }

    public void methodC() {
        clazzA.methodA();
        clazzB.methodB();
    }
}
