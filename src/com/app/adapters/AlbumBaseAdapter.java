package com.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.app.protobuffers.Databuffers.Song;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlbumBaseAdapter{
    private List<Song> songs;
    private List<String> songTitles;
    private LayoutInflater layoutInflater;
    private Context context;
    private int resourceID;
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    public AlbumBaseAdapter(Context context,int resourceID,List<Song> songs)
    {

        this.songs = songs;
        this.context = context;
        this.resourceID = resourceID;


    }
    public List<String> songTitles(){

        for(Song song : songs)
        {
            songTitles.add(song.getTitle());
        }
        return songTitles;
    }
    public ArrayAdapter<String> getAdapter(){

        return new ArrayAdapter<String>(context,resourceID,songTitles);
    }

    public Song getItem(int position)
    {
        return songs.get(position);
    }

}

