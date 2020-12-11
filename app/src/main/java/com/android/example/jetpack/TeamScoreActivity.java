package com.android.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.android.example.R;
import com.android.example.databinding.ActivityTeamScoreBinding;

public class TeamScoreActivity extends AppCompatActivity {

    TeamScoreViewModel viewModel;
    ActivityTeamScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_score);
        viewModel = ViewModelProviders.of(this).get(TeamScoreViewModel.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);
    }
}