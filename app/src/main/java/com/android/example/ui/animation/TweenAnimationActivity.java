package com.android.example.ui.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.android.example.R;

public class TweenAnimationActivity extends AppCompatActivity {

    View targetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);

        targetView = findViewById(R.id.targetView);
    }

    public void translateClicked(View view) {
        //Animation translateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.view_anim_translate);
        Animation translateAnim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1);
        translateAnim.setFillAfter(true);
        translateAnim.setDuration(2000);
        targetView.startAnimation(translateAnim);
    }

    public void rotateClicked(View view) {
        Animation rotateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.view_anim_rotate);
        targetView.startAnimation(rotateAnim);
    }

    public void scaleClicked(View view) {
        Animation scaleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.view_anim_scale);
        targetView.startAnimation(scaleAnim);
    }

    public void alphaClicked(View view) {
        Animation alphaAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.view_anim_alpha);
        targetView.startAnimation(alphaAnim);
    }

    public void setClicked(View view) {
        Animation setAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.view_anim_set);
        targetView.startAnimation(setAnim);
    }
}