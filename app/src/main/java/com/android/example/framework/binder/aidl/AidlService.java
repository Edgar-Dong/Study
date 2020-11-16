package com.android.example.framework.binder.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.example.framework.binder.IMyAidlInterface;
import com.android.example.common.log.Logger;
import com.android.example.util.AppUtil;

public class AidlService extends Service {
    private static final String TAG = "AidlService";
    public AidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private String mName = "None";

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void setName(String name) throws RemoteException {
            Logger.get().d(TAG, AppUtil.getProcessName(AidlService.this) + " mBinder setName name:" + name);
            mName = name;
        }

        @Override
        public String getName() throws RemoteException {
            Logger.get().d(TAG, AppUtil.getProcessName(AidlService.this) + " mBinder getName mName:" + mName);
            return mName;
        }
    };
}
