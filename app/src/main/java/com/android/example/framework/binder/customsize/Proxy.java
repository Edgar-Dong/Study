package com.android.example.framework.binder.customsize;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * @author:無忌
 * @date:2020/10/21
 * @description:
 */
class Proxy implements IMyInterface {
    private IBinder mRemote;

    Proxy(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

    @Override
    public void setCustomName(String name) {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(MyBinder.DESCRIPTOR);
            _data.writeString(name);
            boolean _status = mRemote.transact(MyBinder.TRANSACTION_setName, _data, _reply, 0);
            _reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override
    public String getCustomName() {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        String _result = null;
        try {
            _data.writeInterfaceToken(MyBinder.DESCRIPTOR);
            boolean _status = mRemote.transact(MyBinder.TRANSACTION_getName, _data, _reply, 0);
            _reply.readException();
            _result = _reply.readString();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }
}
