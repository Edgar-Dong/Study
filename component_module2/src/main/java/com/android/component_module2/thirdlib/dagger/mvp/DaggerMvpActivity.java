package com.android.component_module2.thirdlib.dagger.mvp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.component_module2.R;
import com.android.example.common.log.Logger;
import com.android.component_module2.thirdlib.dagger.mvp.model.Model;
import com.android.component_module2.thirdlib.dagger.mvp.model.ThirdClass;
import com.android.component_module2.thirdlib.dagger.mvp.presenter.MainPresenter;
import com.android.component_module2.thirdlib.dagger.mvp.view.MainView;

import javax.inject.Inject;

public class DaggerMvpActivity extends BaseActivity<MainPresenter, MainView> implements MainView {

    @Inject
    Model model;

    @Inject
    ThirdClass thirdClass;

    @Inject
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m2_activity_dagger_mvp);
        presenter.doPresenter();
    }

    @Override
    public void showToast() {
        String message = "model-name:" + model.getName() + ", thirdClass-name:" + thirdClass.getName() + ",sp:" + sp.toString();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Logger.get().d(DaggerMvpActivity.class.getSimpleName(),"showToast:" + message);
    }
}