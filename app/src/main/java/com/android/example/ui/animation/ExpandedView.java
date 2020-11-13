package com.android.example.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.example.R;
import com.android.example.log.Logger;

/**
 * @author:無忌
 * @date:2020/11/13
 * @description:
 */
public class ExpandedView extends FrameLayout {
    private static final String TAG = "ExpandedView";
    TextView mTvAnswer;
    ImageView mIvIndicator;
    boolean isClosed = true;

    public ExpandedView(@NonNull Context context) {
        this(context, null);
    }

    public ExpandedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.expanded_view, this, true);
        LinearLayout llQuestion = view.findViewById(R.id.ll_expanded_question);
        llQuestion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });
        mTvAnswer = view.findViewById(R.id.tv_expanded_answer);
        mIvIndicator = view.findViewById(R.id.iv_expanded_indicator);
    }

    private void anim() {
        ValueAnimator valueAnimator = isClosed ? ValueAnimator.ofFloat(0, 180) : ValueAnimator.ofFloat(180, 0);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mIvIndicator.setRotation(value);
            }
        });
        valueAnimator.start();

        int answerHeight = mTvAnswer.getMeasuredHeight();
        Logger.get().d(TAG, "answerHeight:" + answerHeight);
        MarginLayoutParams layoutParams = (MarginLayoutParams) mTvAnswer.getLayoutParams();
        ValueAnimator valueAnimator1 = isClosed ? ValueAnimator.ofInt(-answerHeight, 0) : ValueAnimator.ofInt(0, -answerHeight);
        valueAnimator1.setDuration(500);
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Logger.get().d(TAG, "anim onAnimationUpdate:" + value);
                layoutParams.bottomMargin = value;
                mTvAnswer.setLayoutParams(layoutParams);
            }
        });
        valueAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isClosed = !isClosed;
            }
        });
        valueAnimator1.start();
    }
}
