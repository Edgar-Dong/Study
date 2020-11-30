package com.android.example.thirdlib.dagger.demo03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;
import com.android.example.thirdlib.dagger.ClazzE;

import javax.inject.Inject;

public class Dagger3Activity extends BaseActivity {

    @Inject
    ClazzE clazzE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger3);
        clazzE.methodE();
    }
}