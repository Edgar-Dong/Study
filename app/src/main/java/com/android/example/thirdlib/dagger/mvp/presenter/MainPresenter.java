package com.android.example.thirdlib.dagger.mvp.presenter;

import com.android.example.thirdlib.dagger.mvp.view.MainView;

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
