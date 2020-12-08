package com.android.example.thirdlib.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@AndroidEntryPoint
public class HiltActivity extends AppCompatActivity {

    @Inject
    Truck truck;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        truck.deliver();
    }
}