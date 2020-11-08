package com.android.example.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.android.example.R;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MemoryLeak2Activity extends AppCompatActivity {

    private TextView tvShowContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);

        tvShowContent = findViewById(R.id.tvShowContent);

        new MyTask(this).execute();
    }

    static class MyTask extends AsyncTask {

        private WeakReference<MemoryLeak2Activity> weakReference;

        public MyTask(MemoryLeak2Activity memoryLeak2Activity){
            weakReference = new WeakReference<>(memoryLeak2Activity);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Date date = new Date();
            if (weakReference.get() != null) {
                weakReference.get().tvShowContent.setText("onPostExecute date:" + date);
            }
        }
    }
}