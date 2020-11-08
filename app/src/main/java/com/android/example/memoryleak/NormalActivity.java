package com.android.example.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}