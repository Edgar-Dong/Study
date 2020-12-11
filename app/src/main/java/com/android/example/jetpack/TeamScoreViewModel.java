package com.android.example.jetpack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author:無忌
 * @date:2020/12/11
 * @description:
 */
public class TeamScoreViewModel extends ViewModel {
    private MutableLiveData<Integer> aTeamScore, bTeamScore;
    private int aBack, bBack;

    public MutableLiveData<Integer> getaTeamScore() {
        if (aTeamScore == null) {
            aTeamScore = new MutableLiveData<>();
            aTeamScore.setValue(0);
        }
        return aTeamScore;
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (bTeamScore == null) {
            bTeamScore = new MutableLiveData<>();
            bTeamScore.setValue(0);
        }
        return bTeamScore;
    }

    public void aTeamAdd(int p) {
        aBack = aTeamScore.getValue();
        bBack = bTeamScore.getValue();
        aTeamScore.setValue(aTeamScore.getValue() + p);
    }

    public void bTeamAdd(int p) {
        aBack = aTeamScore.getValue();
        bBack = bTeamScore.getValue();
        bTeamScore.setValue(bTeamScore.getValue() + p);
    }

    public void reset() {
        aTeamScore.setValue(0);
        bTeamScore.setValue(0);
    }

    public void undo() {
        aTeamScore.setValue(aBack);
        bTeamScore.setValue(bBack);
    }
}
