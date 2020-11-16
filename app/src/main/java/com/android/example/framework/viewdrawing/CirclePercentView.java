package com.android.example.framework.viewdrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.example.R;
import com.android.example.common.log.Logger;

/**
 * @author:無忌
 * @date:2020/11/10
 * @description:
 */
public class CirclePercentView extends View {
    private static final String TAG = "CirclePercentView";
    //大圆半径
    private int mRadius;
    //大圆填充色
    private int mBigCircleColor;
    //扇形填充色
    private int mSmallColor;
    //色带宽度
    private float mStripeWidth;
    //动画位置百分比进度
    private int mCurPercent;
    //中心百分比文字大小
    private float mCenterTextSize;

    //扇形扫过的角度
    private float mSweepAngle;
    //圆心坐标
    private int x, y;
    private int mWidth, mHeight;


    public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        mStripeWidth = a.getDimension(R.styleable.CirclePercentView_stripeWidth, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics()));
        mCurPercent = a.getInteger(R.styleable.CirclePercentView_percent, 0);
        mBigCircleColor = a.getColor(R.styleable.CirclePercentView_bigColor, 0xff6950a1);
        mSmallColor = a.getColor(R.styleable.CirclePercentView_smallColor, 0xffafb4db);
        mCenterTextSize = a.getDimensionPixelSize(R.styleable.CirclePercentView_centerTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics()));
        mRadius = a.getDimensionPixelSize(R.styleable.CirclePercentView_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Logger.get().d(TAG, "onMeasure widthSize:" + widthSize + ", heightSize:" + heightSize + ", widthMode:" + getMeasureSpecMode(widthMode) + ", heightMode:" + getMeasureSpecMode(heightMode));

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = Math.min(widthSize, heightSize) / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mWidth = (int) (mRadius * 2);
            mHeight = (int) (mRadius * 2);
            x = mRadius;
            y = mRadius;
        }

        Logger.get().d(TAG, "onMeasure mWidth:" + mWidth + ", mHeight:" + mHeight + ", x:" + x + ", y:" + y + ", mRadius:" + mRadius);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.get().d(TAG, "onDraw x=" + x + ", y=" + y + ", mWidth=" + mWidth + ", mHeight=" + mHeight);
        //step1绘制大圆
        Paint bigCirclePaint = new Paint();
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(mBigCircleColor);
        canvas.drawCircle(x, y, mRadius, bigCirclePaint);

        //step2绘制扇形
        mSweepAngle = (float) (mCurPercent * 3.6);
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        Paint sectorPaint = new Paint();
        sectorPaint.setAntiAlias(true);
        sectorPaint.setColor(mSmallColor);
        canvas.drawArc(rect, 270, mSweepAngle, true, sectorPaint);

        //step3绘制小圆，颜色透明
        Paint smallCirclePaint = new Paint();
        smallCirclePaint.setAntiAlias(true);
        smallCirclePaint.setColor(mBigCircleColor);
        canvas.drawCircle(x, y, mRadius - mStripeWidth, smallCirclePaint);

        //step4绘制文本
        String text = mCurPercent + "%";
        Paint textPaint = new Paint();
        textPaint.setTextSize(mCenterTextSize);
        float textLength = textPaint.measureText(text);
        textPaint.setColor(Color.WHITE);
        canvas.drawText(text, x - textLength / 2, y, textPaint);
    }

    private void setCurPercent(int percent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 0;
                for (int i = 0; i <= percent; i++) {
                    if (i % 20 == 0) {
                        sleepTime += 2;
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    postInvalidate();
                }
            }
        }).start();
    }

    public void setPercent(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");
        }

        setCurPercent(percent);
    }

    private String getMeasureSpecMode(int value) {
        String modeStr = "";
        switch (value) {
            case MeasureSpec.EXACTLY:
                modeStr = "EXACTLY";
                break;
            case MeasureSpec.AT_MOST:
                modeStr = "AT_MOST";
                break;
            case MeasureSpec.UNSPECIFIED:
                modeStr = "UNSPECIFIED";
                break;
            default:
                break;
        }
        return modeStr;
    }
}
