package com.android.example.framework.dispatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.android.example.log.Logger;

/**
 * @author:無忌
 * @date:2020/11/5
 * @description:
 */
public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "MyRelativeLayout";
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.get().d(TAG,"dispatchTouchEvent " + ev);
        return super.dispatchTouchEvent(ev);
        //return true;
        //return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logger.get().d(TAG,"onInterceptTouchEvent " + ev);
        return super.onInterceptTouchEvent(ev);
        //return true;
        //return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.get().d(TAG,"onTouchEvent " + event);
        return super.onTouchEvent(event);
        //return true;
        //return false;
    }
}
