package com.app.media;


import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */


public class MediaSampler{
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private String filePath;

    public MediaSampler(String filePath) throws IOException {
        this.isPlaying = true;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(filePath);

    }
    public void play(){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            this.isPlaying = true;
        }
    }
    public void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
    public void setVolume(float volume)
    {
        mediaPlayer.setVolume(100,volume);

    }
    public void setPosition(int position)
    {
        mediaPlayer.seekTo(position);
    }
    public float getPosition()
    {
        return mediaPlayer.getCurrentPosition();
    }
}
