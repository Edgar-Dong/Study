package com.android.example.framework.dispatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.example.log.Logger;

/**
 * @author:無忌
 * @date:2020/11/5
 * @description:
 */
public class MyTextView extends AppCompatTextView {
    private static final String TAG = "MyTextView";

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Logger.get().d(TAG, "dispatchTouchEvent " + event);
        //return super.dispatchTouchEvent(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.get().d(TAG, "onTouchEvent " + event);
        return super.onTouchEvent(event);
        //return true;
    }
}
