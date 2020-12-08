package com.android.component_module2.thirdlib.dagger.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.component_module2.thirdlib.dagger.mvp.presenter.BasePresenter;
import com.android.component_module2.thirdlib.dagger.mvp.view.IBaseView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
public class BaseActivity<T extends BasePresenter<K>, K extends IBaseView> extends AppCompatActivity implements IBaseView {

    @Inject
    T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        presenter.attachView((K) this);
    }
}
