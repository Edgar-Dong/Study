package com.android.example.jetpack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author:無忌
 * @date:2020/12/10
 * @description:
 */
public class ViewModelWithLiveData extends ViewModel {
    private MutableLiveData<Integer> likedNumber;

    public MutableLiveData<Integer> getLikedNumber() {
        if (likedNumber == null) {
            likedNumber = new MutableLiveData<>();
            likedNumber.setValue(0);
        }
        return likedNumber;
    }

    public void addLikedNumber(int n) {
        likedNumber.setValue(likedNumber.getValue() + n);
    }

    public void add(){
        likedNumber.setValue(likedNumber.getValue() + 1);
    }
}
