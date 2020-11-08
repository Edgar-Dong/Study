package com.android.example.framework.binder.customsize;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/**
 * @author:無忌
 * @date:2020/10/21
 * @description:
 */
abstract class MyBinder extends Binder implements IMyInterface {
    static final String DESCRIPTOR = "com.android.example.framework.binder.customsize.IMyInterface";
    static final int TRANSACTION_setName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public MyBinder() {
        attachInterface(this, DESCRIPTOR);
    }

    public static IMyInterface asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IMyInterface))) {
            return ((IMyInterface) iin);
        }
        return new Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        String descriptor = DESCRIPTOR;
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }
            case TRANSACTION_setName: {
                data.enforceInterface(descriptor);
                String _arg0 = data.readString();
                setCustomName(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_getName: {
                data.enforceInterface(descriptor);
                String _result = getCustomName();
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
