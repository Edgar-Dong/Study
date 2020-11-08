package com.android.example.ui.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.airbnb.lottie.LottieTask;
import com.android.example.R;
import com.android.example.log.Logger;

/**
 * @author:無忌
 * @date:2020/10/26
 * @description:
 * 复杂动画处理
 * http://airbnb.io/lottie/#/
 */
public class LottieActivity extends AppCompatActivity {
    private static final String TAG = "LottieActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        LottieAnimationView animationView = findViewById(R.id.animationView);
        animationView.setRepeatCount(5);
        animationView.setRepeatMode(LottieDrawable.RESTART);
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Logger.get().d(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Logger.get().d(TAG, "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Logger.get().d(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Logger.get().d(TAG, "onAnimationRepeat");
            }
        });
        animationView.addLottieOnCompositionLoadedListener(new LottieOnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                Logger.get().d(TAG, "onCompositionLoaded " + composition.toString());
            }
        });
        LottieTask<LottieComposition> lottieTask = LottieCompositionFactory.fromAsset(this, "AndroidWave.json");
        lottieTask.addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {
                Logger.get().d(TAG, "onResult " + result.toString());
                animationView.setComposition(result);
                animationView.playAnimation();
            }
        });
    }
}