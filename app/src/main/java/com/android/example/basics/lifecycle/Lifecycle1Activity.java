package com.android.example.basics.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.example.R;
import com.android.example.common.log.Logger;

public class Lifecycle1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.get().d(Lifecycle1Activity.class.getSimpleName(),"onRestart");
    }

    public void startNewPage(View view) {
        Intent intent = new Intent(this, Lifecycle2Activity.class);
        startActivity(intent);
    }
}