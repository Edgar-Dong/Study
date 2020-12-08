package com.android.component_module2.thirdlib.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
@Singleton
public class ClazzD {
    @Inject
    public ClazzD() {

    }

    private String name;
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
//        return "ClazzD{" +
//                "name='" + name + '\'' +
//                ", addr='" + addr + '\'' +
//                '}';
        return super.toString();
    }
}
