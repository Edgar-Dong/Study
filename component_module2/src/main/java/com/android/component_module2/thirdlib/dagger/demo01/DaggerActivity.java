package com.android.component_module2.thirdlib.dagger.demo01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.component_module2.R;
import com.android.example.common.log.Logger;
import com.android.component_module2.thirdlib.dagger.ClazzA;
import com.android.component_module2.thirdlib.dagger.ClazzC;
import com.android.component_module2.thirdlib.dagger.ClazzD;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {
    @Inject
    ClazzA clazzA;
    @Inject
    ClazzC clazzC;

    @Inject
    ClazzD clazzD1;

    @Inject
    ClazzD clazzD2;

//    @Inject
//    ClazzE clazzE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m2_activity_dagger);

        DaggerAppComponent.builder().build().inject(this);

        clazzA.methodA();
        clazzC.methodC();

        clazzD1.setName("clazzD1");
        Logger.get().d(DaggerActivity.class.getSimpleName(), "clazzD1:" + clazzD1.toString() + ", clazzD2:" + clazzD2.toString());

//        clazzE.methodE();
    }
}