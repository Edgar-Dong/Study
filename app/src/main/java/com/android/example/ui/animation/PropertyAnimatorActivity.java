package com.android.example.ui.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.example.R;

public class PropertyAnimatorActivity extends AppCompatActivity {

    ImageView targetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);

        targetView = findViewById(R.id.targetView);
    }

    public void rotateClicked(View view) {
//        ObjectAnimator.ofFloat(targetView, "rotationY", 0f, 360f)
//                .setDuration(2000)
//                .start();

//        Animator rotateAnimator = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.property_animator);
//        rotateAnimator.setTarget(targetView);
//        rotateAnimator.start();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,180);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                targetView.setRotation((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    public void setClicked(View view) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(targetView, "scaleX", 1.0f, 0.5f);
        scaleXAnimator.setDuration(2000);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(targetView, "scaleY", 1.0f, 0.5f);
        scaleYAnimator.setDuration(2000);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(targetView, "translationX", 0, targetView.getWidth() / 2);
        translationX.setDuration(2000);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(targetView, "translationY", 0, targetView.getHeight() / 2);
        translationY.setDuration(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        //animatorSet.play(scaleXAnimator).with(scaleYAnimator).with(translationX).with(translationY);
        //含义：after：执行完after的动画才会执行play；with：和play动画一起执行；before:执行before动画前执行play
        animatorSet.play(scaleXAnimator).with(scaleYAnimator).after(translationX).before(translationY);
        animatorSet.start();
    }

    public void practiceClicked(View view) {
        Intent intent = new Intent(this,PracticeActivity.class);
        startActivity(intent);
    }
}