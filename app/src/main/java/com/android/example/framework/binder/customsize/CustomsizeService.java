package com.android.example.framework.binder.customsize;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.android.example.common.log.Logger;
import com.android.example.util.AppUtil;

public class CustomsizeService extends Service {
    private static final String TAG = "BinderService";

    public CustomsizeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    private String mName = "None";

    MyBinder myBinder = new MyBinder() {
        @Override
        public void setCustomName(String name) {
            Logger.get().d(TAG, AppUtil.getProcessName(CustomsizeService.this) + " myBinder setName name:" + name);
            mName = name;
        }

        @Override
        public String getCustomName() {
            Logger.get().d(TAG, AppUtil.getProcessName(CustomsizeService.this) + " mBinder getName mName:" + mName);
            return mName;
        }
    };
}
