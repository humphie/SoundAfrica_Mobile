package com.app.managers;

import android.content.Context;
import com.app.user.Credentials;
import com.app.protobuffers.Databuffers.Album;
import com.app.protobuffers.OrderBuffer.Order;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */


public class OrderManager {
    private OrderProcessor orderProcessor;
    private NetworkManager networkManager;
    private Credentials credentials;
    private Album album;
    private Order repliedOrder;
    private Context context;
    private boolean creditPass;
    private FileDownloadManager fileDownloadManager;
    public OrderManager(Context context, Credentials credentials,NetworkManager networkManager,Album album){

        this.context = context;
        this.credentials = credentials;
        this.networkManager = networkManager;
        this.album = album;

    }

    public boolean processOrder() throws IOException {
        networkManager.connectToUrl("http://www.wave.com/orderprocess/?");
        orderProcessor = new OrderProcessor(networkManager);
        orderProcessor.createOrder(credentials,album);
        if(orderProcessor.sendOrder())
        {
            ServerReply info = orderProcessor.serverResponse();
            if(info.equals(ServerReply.GOT_FILE)){
                repliedOrder = orderProcessor.getServerReply();

            }
            if(info.equals(ServerReply.RESUME_DOWNLOAD)){}
            if(info.equals(ServerReply.FINISHED_DOWNLOAD)){}
        }
        return false;
    }

    public void processDownload(){

        // Call The File DownLoader
        fileDownloadManager = new FileDownloadManager(context,repliedOrder.getArchivePath(),networkManager);
        fileDownloadManager.startDownload();
        // Extract the file

        try {
            fileDownloadManager.extract();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Delete the file archive to conserve space
    }
    public boolean checkCreditStatus()
    {
        if(repliedOrder.getCredit() >= album.getPrice()){
          creditPass = true;
        } else{
            creditPass = false;
        }

        return creditPass;
    }
    public int getCurrentCredit(){

        return repliedOrder.getCredit();
    }

}


