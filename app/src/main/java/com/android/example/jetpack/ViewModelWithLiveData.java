package com.android.example.jetpack;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

/**
 * @author:無忌
 * @date:2020/12/10
 * @description:
 */
public class ViewModelWithLiveData extends AndroidViewModel {
    private static final String KEY_LIKED_NUMBER = "liked_number";
    private static final String SHP_NAME = "shp_name";

    private SavedStateHandle handle;

    public ViewModelWithLiveData(Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(KEY_LIKED_NUMBER)) {
            load();
        }
    }

    private void load() {
        SharedPreferences sp = getApplication().getSharedPreferences(SHP_NAME, Context.MODE_PRIVATE);
        int likedNumber = sp.getInt(KEY_LIKED_NUMBER, 0);
        handle.set(KEY_LIKED_NUMBER, likedNumber);
    }

    public void save() {
        SharedPreferences sp = getApplication().getSharedPreferences(SHP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_LIKED_NUMBER, getLikedNumber().getValue());
        editor.apply();
    }

    public MutableLiveData<Integer> getLikedNumber() {
        return handle.getLiveData(KEY_LIKED_NUMBER, 0);
    }

    public void add(int n) {
        handle.set(KEY_LIKED_NUMBER, getLikedNumber().getValue() + n);
    }
}
