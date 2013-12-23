package com.app.managerUIs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.app.SoundFrica.R;
import com.app.adapters.AlbumBaseAdapter;
import com.app.media.MediaSampler;
import com.app.protobuffers.Databuffers.Song;
import com.app.protobuffers.Databuffers.Album;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */


public class AlbumManagerUI extends Activity {

    private View viewContainer;
    private MediaSampler audioSampler;
    private ListView songList;
    private List<Song> songs;
    private SeekBar seekBar;
    private ImageView coverArt;
    private TextView albumName;
    private TextView artistName;
    private Button orderButton;
    private Intent prevIntent = getIntent();
    private AlbumBaseAdapter albumBaseAdapter;

    private Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_manager_view);
        songList = (ListView) findViewById(R.id.trackView);
        viewContainer = findViewById(R.id.samplerBar);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        coverArt = (ImageView)findViewById(R.id.coverArt);
        albumName = (TextView)findViewById(R.id.albumTitle);
        artistName = (TextView)findViewById(R.id.artist);
        // SET THE COVER ART , ALBUM NAME & ARTIST NAME
        album = (Album)prevIntent.getSerializableExtra("AlbumObject");
        byte bitmap[] = album.getCoverArt().toByteArray();
        coverArt.setImageBitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length)); // BIG MESS OF CODE RIGHT ?
        albumName.setText(album.getAlbumName());
        artistName.setText(album.getAlbumArtist());
        albumBaseAdapter = new AlbumBaseAdapter(this,R.id.trackView,songs);
        songList.setAdapter(albumBaseAdapter.getAdapter());

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                Object o = songList.getItemAtPosition(position);
                Song song = (Song)o;
                player(viewContainer);
                try {
                    audioSampler = new MediaSampler(song.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void onClick(View view) {

        if(view.getId() == R.id.playPause_button){
            Toast.makeText(this, "Sampling in Progress", Toast.LENGTH_LONG).show();
            audioSampler.play();
            if(view.getId() == R.id.order){

                Intent nextIntent = new Intent(AlbumManagerUI.this,OrderManagerUI.class);
                nextIntent.putExtra("selectedAlbum",album);
                startActivity(nextIntent); // Finally you get to order for something
            }
        }
        viewContainer.setVisibility(View.GONE);
    }

    public static void player(final View viewContainer) {
        viewContainer.setVisibility(View.VISIBLE);

        viewContainer.setVisibility(View.GONE);
    }
}
