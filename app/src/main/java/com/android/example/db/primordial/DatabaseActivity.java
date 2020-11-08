package com.android.example.db.primordial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.example.R;
import com.android.example.log.Logger;

public class DatabaseActivity extends AppCompatActivity {
    private static final String TAG = "DatabaseActivity";

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        databaseManager = DatabaseManager.getInstance(getApplicationContext());

    }

    public void insert(View view) {
        databaseManager.insertStudentAndCourse();
    }

    public void delete(View view) {
        databaseManager.deleteCourse();
    }

    public void query(View view) {
        databaseManager.querStudentAndCourse();
    }

    public void update(View view) {
        databaseManager.updateStudent();
    }
}