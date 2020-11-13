package com.android.example.ui.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.example.R;

public class FrameAnimationActivity extends AppCompatActivity {

    ImageView targetView1;
    ImageView targetView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        targetView1 = findViewById(R.id.targetView1);
        targetView2 = findViewById(R.id.targetView2);
    }

    public void start1Clicked(View view) {
        AnimationDrawable ad = new AnimationDrawable();
        for (int i = 0; i < 6; i++) {
            Drawable drawable = getResources().getDrawable(getResources().getIdentifier("ic_fingerprint_" + i, "drawable", getPackageName()));
            ad.addFrame(drawable, 100);
        }
        ad.setOneShot(false);
        targetView1.setImageDrawable(ad);
        ad.start();
    }

    public void start2Clicked(View view) {
        targetView2.setImageDrawable(getResources().getDrawable(R.drawable.frame_anim));
        AnimationDrawable animationDrawable = (AnimationDrawable) targetView2.getDrawable();
        animationDrawable.start();
    }
}