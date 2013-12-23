package com.app.managerUIs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.app.SoundFrica.SoundAfricaMain;
import com.app.adapters.CollectionBaseAdapter;
import com.app.loaders.CollectionLoader;
import com.app.managers.NetworkManager;
import com.app.protobuffers.Databuffers.Album;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */


public class CollectionManagerUI extends Activity {

    private CollectionLoader collectionLoader;
    private Album selectedAlbum;
    private List<Album> albums;
    private ListView collectionView;
    private NetworkManager networkManager;


    public void onCreate(Bundle savedInstanceState) {
        networkManager = new NetworkManager();
        collectionLoader = new CollectionLoader(networkManager);




        super.onCreate(savedInstanceState);
        if(!collectionLoader.isConnectionOK()){

            // W@ Should we do if their is no connection ?
            Toast.makeText(this,"OOPS!!! NETWORK DOWN",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CollectionManagerUI.this,SoundAfricaMain.class);
            intent.putExtra("failed-start","failed-start");

            startActivity(intent);
            this.finish();


        }else{
        albums = collectionLoader.getAlbums();
        collectionView.setAdapter(new CollectionBaseAdapter(this,albums));
        collectionView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                Object o = collectionView.getItemAtPosition(position);
                selectedAlbum =  (Album)o;
                Intent intent = new Intent(CollectionManagerUI.this,AlbumManagerUI.class);
                intent.putExtra("AlbumObject", selectedAlbum);
                startActivity(intent);
            }});
    }
    }
}

