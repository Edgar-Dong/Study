package com.android.example.ui.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.example.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    public void tweenAnimationClicked(View view) {
        Intent intent = new Intent(this, TweenAnimationActivity.class);
        startActivity(intent);
    }

    public void propertyAnimationClicked(View view) {
        Intent intent = new Intent(this, PropertyAnimatorActivity.class);
        startActivity(intent);
    }

    public void frameAnimationClicked(View view) {
        Intent intent = new Intent(this,FrameAnimationActivity.class);
        startActivity(intent);
    }
}