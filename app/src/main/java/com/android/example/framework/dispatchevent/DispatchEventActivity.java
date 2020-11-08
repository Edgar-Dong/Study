package com.android.example.framework.dispatchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.android.example.R;
import com.android.example.log.Logger;

public class DispatchEventActivity extends AppCompatActivity {
    private static final String TAG = "DispatchEventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event);
        Logger.get().d(TAG,"onCreate");

//        findViewById(R.id.myViewGroup).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //Logger.get().d(TAG,"myViewGroup onTouch " + event);
//                return false;
//            }
//        });
//        findViewById(R.id.myViewGroup).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Logger.get().d(TAG,"myViewGroup onClick");
//            }
//        });
//        findViewById(R.id.myView).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //Logger.get().d(TAG,"myView onTouch " + event);
//                return false;
//            }
//        });
//        findViewById(R.id.myView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Logger.get().d(TAG,"myView onClick");
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.get().d(TAG,"dispatchTouchEvent " + ev);
        return super.dispatchTouchEvent(ev);
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