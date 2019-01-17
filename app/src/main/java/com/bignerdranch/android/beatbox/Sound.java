package com.bignerdranch.android.beatbox;

/**
 * Created by Mason on 2/25/2018.
 * sound object keeps track of file name, name user sees, ect
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    //constructor
    public Sound (String assetPath){
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length-1];
        mName = filename.replace(".wav", "");
    }

    //getters
    public String getAssetPath(){
        return mAssetPath;
    }

    public String getName(){
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
