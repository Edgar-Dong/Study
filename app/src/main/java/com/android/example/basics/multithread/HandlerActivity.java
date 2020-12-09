package com.android.example.basics.multithread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.example.R;
import com.android.example.common.log.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        Handler mainHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        Logger.get().d(HandlerActivity.class.getSimpleName(), "收到子线程消息:" + msg.obj);
                        break;
                    default:
                        break;
                }
            }
        };

        Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("员工");
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String result = threadName + ":" + work();
                Message message = mainHandler.obtainMessage(1, result);
                message.sendToTarget();
            }

            private String work() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "完成领导安排的工作任务";
            }
        });
    }
}