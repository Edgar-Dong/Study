package com.android.example.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.android.example.R;
import com.android.example.log.Logger;

public class MemoryLeakActivity extends AppCompatActivity {

    private String test = "TEST_STR";
    private Handler handler = new Handler();

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            Logger.get().d(MyRunnable.class.getSimpleName(), test);
            handler.postDelayed(this,5000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        handler.postDelayed(new MyRunnable(), 5000);
    }
}