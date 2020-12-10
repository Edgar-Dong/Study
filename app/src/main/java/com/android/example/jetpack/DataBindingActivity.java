package com.android.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.android.example.R;
import com.android.example.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {
    ViewModelWithLiveData viewModel;
    ActivityDataBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding);

        viewModel = ViewModelProviders.of(this).get(ViewModelWithLiveData.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);
    } 
}