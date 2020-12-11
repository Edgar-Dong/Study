package com.android.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.example.R;

public class LiveDataActivity extends AppCompatActivity {

    ViewModelWithLiveData viewModelWithLiveData;
    TextView textView;
    ImageButton imageButtonLike, imageButtonDisLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        textView = findViewById(R.id.textView);
        imageButtonLike = findViewById(R.id.imageButton);
        imageButtonDisLike = findViewById(R.id.imageButton2);
        viewModelWithLiveData = ViewModelProviders.of(this,new SavedStateViewModelFactory(getApplication(),this)).get(ViewModelWithLiveData.class);
        viewModelWithLiveData.getLikedNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });
        imageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelWithLiveData.add(1);
            }
        });
        imageButtonDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelWithLiveData.add(-1);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModelWithLiveData.save();
    }
}