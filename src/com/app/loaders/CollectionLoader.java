package com.app.loaders;

import java.io.IOException;
import java.util.List;
import com.app.managers.NetworkManager;
import com.app.managers.Status;
import com.app.protobuffers.Databuffers.Collection;
import com.app.protobuffers.Databuffers.Album;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */


public class CollectionLoader {
    private Collection collection;
    private List<Album> albums;
    private boolean connectionOK;
    private NetworkManager networkManager;



    public CollectionLoader(NetworkManager networkManager)
    {

        this.networkManager = networkManager;


    }

    public void getCollectionOnline()
    {
        networkManager.connectToUrl("http://www.soundafrica.com/collection");

        if(networkManager.getStatus()== Status.CONNECTION_OK){
        try {

            collection = Collection.parseFrom(networkManager.getInputStream());
            setAlbums(collection.getAlbumsList());
            this.setConnectionOK(true);

        } catch (IOException e) {
            this.setConnectionOK(false);
            e.printStackTrace();
        }
    } else{
            this.setConnectionOK(false);
        }
    }

    public Collection getCollection() {
        return collection;
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public boolean isConnectionOK() {
        return connectionOK;
    }

    public void setConnectionOK(boolean connectionOK) {
        this.connectionOK = connectionOK;
    }
}
