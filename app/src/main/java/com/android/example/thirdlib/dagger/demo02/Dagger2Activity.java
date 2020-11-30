package com.android.example.thirdlib.dagger.demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;
import com.android.example.common.log.Logger;
import com.android.example.thirdlib.dagger.ClazzA;
import com.android.example.thirdlib.dagger.ClazzC;
import com.android.example.thirdlib.dagger.ClazzD;
import com.android.example.thirdlib.dagger.demo01.DaggerActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class Dagger2Activity extends AppCompatActivity {
    @Inject
    ClazzA clazzA;
    @Inject
    ClazzC clazzC;

    @Inject
    ClazzD clazzD1;

    @Inject
    ClazzD clazzD2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_dagger2);

        clazzA.methodA();
        clazzC.methodC();

        clazzD1.setName("clazzD1");
        Logger.get().d(Dagger2Activity.class.getSimpleName(), "clazzD1:" + clazzD1.toString() + ", clazzD2:" + clazzD2.toString());
    }
}