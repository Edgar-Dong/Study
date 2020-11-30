package com.android.example.thirdlib.dagger.demo01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.R;
import com.android.example.common.log.Logger;
import com.android.example.thirdlib.dagger.ClazzA;
import com.android.example.thirdlib.dagger.ClazzC;
import com.android.example.thirdlib.dagger.ClazzD;
import com.android.example.thirdlib.dagger.ClazzE;

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
        setContentView(R.layout.activity_dagger);

        DaggerAppComponent.builder().build().inject(this);

        clazzA.methodA();
        clazzC.methodC();

        clazzD1.setName("clazzD1");
        Logger.get().d(DaggerActivity.class.getSimpleName(), "clazzD1:" + clazzD1.toString() + ", clazzD2:" + clazzD2.toString());

//        clazzE.methodE();
    }
}