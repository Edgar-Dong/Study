package com.android.example.db.greendao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.example.R;
import com.android.example.log.Logger;
import com.android.example.ui.bitmap.BitmapActivity;

import java.util.Arrays;

public class GreenDaoActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    GreenDaoManager greenDaoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        //GreenDaoManager.importDb(getApplicationContext());

        if (GreenDaoManager.CHANGE_DB_PATH) {
            verifyStoragePermissions(this);
        } else {
            greenDaoManager = GreenDaoManager.getInstance(getApplicationContext());
        }
    }

    public void insert(View view) {
        greenDaoManager.insertNote();
    }

    public void delete(View view) {
        greenDaoManager.deleteNote();
    }

    public void update(View view) {
        greenDaoManager.updateNote();
    }

    public void query(View view) {
        greenDaoManager.queryNote();
    }

    public void deleteDb(View view) {
        greenDaoManager.deleteDb();
    }

    private void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        Logger.get().d(BitmapActivity.class.getSimpleName(), "verifyStoragePermissions permission:" + permission);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        } else {
            greenDaoManager = GreenDaoManager.getInstance(getApplicationContext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.get().d(BitmapActivity.class.getSimpleName(), "onRequestPermissionsResult requestCode:" + requestCode + ", permissions:" + Arrays.toString(permissions) + ", grantResults:" + Arrays.toString(grantResults));
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被同意
                greenDaoManager = GreenDaoManager.getInstance(getApplicationContext());
            } else {
                //权限被拒绝
                Toast.makeText(this, "申请的读写SD卡权限被拒绝！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}