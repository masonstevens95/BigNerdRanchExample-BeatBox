package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mason on 2/25/2018.
 * Asset management class
 */

public class BeatBox {
    //constants  for folder and debugging
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    //get assetmanager from context
    private AssetManager mAssets;
    //create name for sounds
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    //constructor to get assetmanager from context
    public BeatBox(Context context){
        mAssets = context.getAssets();
        //old constructor is depreciated but needed for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);

        //load sounds
        loadSounds();
    }

    //plays sounds
    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if (soundId == null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    //cleans up soundpool
    public void release(){
        mSoundPool.release();
    }

    //trys to list sound filenames if they exist
    private void loadSounds(){
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe){
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        //create some sounds
        for (String filename : soundNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                //calls sounds
                load(sound);
                mSounds.add(sound);
            }catch (IOException ioe){
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    //loads a sound into soundpool; msoundPool.load will return an int ID
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    //return the list of sounds
    public List<Sound> getSounds(){
        return mSounds;
    }
}
