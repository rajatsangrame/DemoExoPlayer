package com.example.rajat.demo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Rajat Sangrame on 29/2/20.
 * http://github.com/rajatsangrame
 */
public class MainActivityViewModel extends ViewModel {


    private MutableLiveData<Integer> liveScore = new MutableLiveData<>();

    public MutableLiveData<Integer> getLiveScore() {
        return liveScore;
    }

    public void updateLiveScore(Integer score) {
        int prevScore = 0;
        if (this.liveScore.getValue() != null) {
            prevScore = this.liveScore.getValue();
        }
        score = prevScore + score;
        this.liveScore.postValue(score);
    }

}
