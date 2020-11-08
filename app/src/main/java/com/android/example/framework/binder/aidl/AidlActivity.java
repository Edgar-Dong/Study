package com.android.example.framework.binder.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.android.example.R;
import com.android.example.framework.binder.IMyAidlInterface;

public class AidlActivity extends AppCompatActivity {

    TextView textName;
    IMyAidlInterface myAidlInterface;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                textName.setText(myAidlInterface.getName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        textName = findViewById(R.id.textName);

        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void setName(View view) {
        try {
            myAidlInterface.setName("China");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getName(View view) {
        try {
            String name = myAidlInterface.getName();
            textName.setText(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}