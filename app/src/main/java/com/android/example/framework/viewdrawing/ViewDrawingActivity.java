package com.android.example.framework.viewdrawing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.example.R;
import com.android.example.common.log.Logger;

import java.util.Random;

public class ViewDrawingActivity extends AppCompatActivity {
    private static final String TAG = "ViewDrawingActivity";
    CirclePercentView circlePercentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drawing);
        circlePercentView = findViewById(R.id.circlePercentView);
    }

    public void change(View view) {
        Random random = new Random();
        int percent = random.nextInt(100);
        Logger.get().d(TAG, "change percent:" + percent);
        circlePercentView.setPercent(percent);
    }
}