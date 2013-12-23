package com.app.managers;

import com.app.user.Credentials;
import java.io.IOException;
import java.io.OutputStream;
import com.app.protobuffers.OrderBuffer.Order;
import com.app.protobuffers.Databuffers.Album;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */


public class OrderProcessor {
    private NetworkManager networkManager;
    private Album album;

    private Credentials credentials;
    private Order.Builder newOrder;
    private Order repliedOrder;
    private OutputStream outputStream;

    public OrderProcessor(NetworkManager networkManager){

        this.networkManager = networkManager;
        this.album = album;
        this.credentials = credentials;
    }

    public void createOrder(Credentials credentials,Album album)
    {
        newOrder.setUsername(credentials.getUserName());
        newOrder.setPassword(credentials.getPassword());
        newOrder.setAlbumName(album.getAlbumName());
        newOrder.setArtist(album.getAlbumArtist());
        if(album.getValue().equals(Order.AlbumType.FREE)){
            newOrder.setType(Order.AlbumType.FREE);
        }
        else if(album.getValue().equals(Order.AlbumType.NONFREE)){
            newOrder.setType(Order.AlbumType.NONFREE);
        }
        else{
            newOrder.setType(Order.AlbumType.PROMO);
        }
        newOrder.build();
    }
    public boolean sendOrder() throws IOException {
        outputStream = networkManager.getOutputStream();
        try {
            newOrder.build().writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ServerReply serverResponse() throws IOException {

        Order.parseFrom(networkManager.getInputStream());
        ServerReply serverData = null;

        if(repliedOrder.getJob().equals(Order.Job.NOTDONE)){
            serverData =  ServerReply.GOT_FILE;
        }
        if(repliedOrder.getJob().equals(Order.Job.FAILED)){
            serverData =  ServerReply.RESUME_DOWNLOAD;
        }
        if(repliedOrder.getJob().equals(Order.Job.DONE))
        {
            serverData = ServerReply.FINISHED_DOWNLOAD;
        }
        return serverData;

    }
    public Order getServerReply()
    {
        return repliedOrder;
    }
}

