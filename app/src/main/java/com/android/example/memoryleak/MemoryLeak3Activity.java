package com.android.example.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.example.R;
import com.android.example.common.log.Logger;

public class MemoryLeak3Activity extends AppCompatActivity {

    TextView tvShowContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak3);

        tvShowContent = findViewById(R.id.tvShowContent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MySingleton mySingleton = MySingleton.getInstance(this);
        Logger.get().d(MemoryLeak3Activity.class.getSimpleName(),"onResume mySingleton:" + mySingleton);
        tvShowContent.setText(mySingleton.getName());
    }
}