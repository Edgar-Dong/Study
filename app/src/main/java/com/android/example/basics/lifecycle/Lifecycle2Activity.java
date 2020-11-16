package com.android.example.basics.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;
import com.android.example.common.log.Logger;

public class Lifecycle2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.get().d(Lifecycle2Activity.class.getSimpleName(),"onRestart");
    }
}