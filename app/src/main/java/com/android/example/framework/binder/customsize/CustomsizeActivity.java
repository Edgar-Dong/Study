package com.android.example.framework.binder.customsize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.android.example.R;

public class CustomsizeActivity extends AppCompatActivity {

    TextView textName;
    IMyInterface myInterface;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myInterface = MyBinder.asInterface(service);
            textName.setText(myInterface.getCustomName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);

        textName = findViewById(R.id.textName);

        Intent intent = new Intent(this, CustomsizeService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void setName(View view) {
        myInterface.setCustomName("Usa");
    }

    public void getName(View view) {
        textName.setText(myInterface.getCustomName());
    }
}