package com.android.example.thirdlib.dagger.mvp.model;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
public class Model {
    @Inject
    public Model() {
        name = "Model";
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
