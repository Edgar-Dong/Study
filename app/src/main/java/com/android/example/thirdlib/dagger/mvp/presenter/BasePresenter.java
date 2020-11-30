package com.android.example.thirdlib.dagger.mvp.presenter;

import com.android.example.thirdlib.dagger.mvp.view.IBaseView;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter {
    private T view;

    public T getView() {
        return view;
    }

    public void attachView(T view) {
        this.view = view;
    }
}
