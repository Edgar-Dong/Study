package com.android.example.jetpack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

/**
 * @author:無忌
 * @date:2020/12/10
 * @description:
 */
public class ViewModelWithLiveData extends ViewModel {
    private static final String KEY_LIKED_NUMBER = "liked_number";
    private SavedStateHandle handle;

    public ViewModelWithLiveData(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getLikedNumber() {
        if (!handle.contains(KEY_LIKED_NUMBER)) {
            handle.set(KEY_LIKED_NUMBER, 0);
        }
        return handle.getLiveData(KEY_LIKED_NUMBER,0);
    }

    public void addLikedNumber(int n) {
        handle.set(KEY_LIKED_NUMBER, getLikedNumber().getValue() + n);
    }

    public void add() {
        handle.set(KEY_LIKED_NUMBER, getLikedNumber().getValue() + 1);
    }
}
