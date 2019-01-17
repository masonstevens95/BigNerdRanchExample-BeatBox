package com.bignerdranch.android.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Mason on 2/25/2018.
 * decides how things are displayed
 */

public class SoundViewModel extends BaseObservable{
    private Sound mSound;
    private BeatBox mBeatBox;

    //constructor
    public SoundViewModel(BeatBox beatBox){
        mBeatBox = beatBox;
    }

    //gets title the button should display
    @Bindable
    public String getTitle(){
        return mSound.getName();
    }

    //getter and setter
    public Sound getSound(){
        return mSound;
    }

    public void setSound(Sound sound){
        mSound = sound;
        //notifies binding class that all @bindables have been updated, reruning the code in the layout mustaches
        notifyChange();
    }

    public void onButtonClicked() {
        mBeatBox.play(mSound);
    }
}
