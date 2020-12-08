package com.android.component_module2.thirdlib.dagger.mvp.component;

import com.android.component_module2.thirdlib.dagger.mvp.BaseActivity;
import com.android.component_module2.thirdlib.dagger.mvp.presenter.BasePresenter;
import com.android.component_module2.thirdlib.dagger.mvp.view.IBaseView;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface ActivityComponet extends AndroidInjector<BaseActivity<BasePresenter<IBaseView>,IBaseView>> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity<BasePresenter<IBaseView>,IBaseView>> {
    }
}
