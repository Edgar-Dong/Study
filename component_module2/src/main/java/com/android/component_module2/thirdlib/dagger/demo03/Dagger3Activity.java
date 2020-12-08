package com.android.component_module2.thirdlib.dagger.demo03;

import android.os.Bundle;

import com.android.component_module2.R;
import com.android.component_module2.thirdlib.dagger.ClazzE;

import javax.inject.Inject;

public class Dagger3Activity extends BaseActivity {

    @Inject
    ClazzE clazzE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m2_activity_dagger3);
        clazzE.methodE();
    }
}