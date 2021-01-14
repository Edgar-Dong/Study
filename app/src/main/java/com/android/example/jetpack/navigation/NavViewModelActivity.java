package com.android.example.jetpack.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.example.R;

public class NavViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view_model);
        Log.e("---", "hah");
        Log.d("---", "hah");
        Log.w("---", "hah");
    }
}