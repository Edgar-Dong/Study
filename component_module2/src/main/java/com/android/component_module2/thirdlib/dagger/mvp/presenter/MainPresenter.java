package com.android.component_module2.thirdlib.dagger.mvp.presenter;

import com.android.component_module2.thirdlib.dagger.mvp.view.MainView;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
public class MainPresenter extends BasePresenter<MainView> {
    @Inject
    public MainPresenter(){

    }

    public void doPresenter(){
        getView().showToast();
    }
}
