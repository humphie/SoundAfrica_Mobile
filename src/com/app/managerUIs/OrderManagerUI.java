package com.app.managerUIs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.app.Databases.CredentialsRepository;
import com.app.SoundFrica.R;
import com.app.dialogs.PaymentAcceptanceDialog;
import com.app.managers.NetworkManager;
import com.app.managers.OrderManager;
import com.app.protobuffers.Databuffers.Album;
import com.app.user.Credentials;


import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class OrderManagerUI extends Activity {

    private EditText albumName;
    private EditText albumPrice;
    private ImageView coverArt;
    private Button button;
    private Album album;
    private CredentialsRepository credentialsRepository;
    View promptView = null;
    private OrderManager orderManager;
    private PaymentAcceptanceDialog paymentAcceptanceDialog;
    private Intent intent = getIntent();
    private NetworkManager networkManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_view);
        albumName = (EditText)findViewById(R.id.albumName);
        albumPrice = (EditText)findViewById(R.id.price);
        button = (Button)findViewById(R.id.download);
        coverArt = (ImageView)findViewById(R.id.coverArt);
        // SET THE COVER ART , ALBUM NAME & ARTIST NAME
        album = (Album)intent.getSerializableExtra("AlbumObject");
        byte bitmap[] = album.getCoverArt().toByteArray();
        coverArt.setImageBitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length)); // BIG MESS OF CODE RIGHT ?
        albumName.setText(album.getAlbumName());
        albumPrice.setText(album.getPrice());
        credentialsRepository = new CredentialsRepository(this);
        networkManager = new NetworkManager();
        Credentials credential = null;
        try {
            credential = credentialsRepository.getCredentials();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderManager =  new OrderManager(this,credential,networkManager,album);
        try {
            orderManager.processOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view)
    {
        if(view.getId() == R.id.download){
            if(orderManager.checkCreditStatus())
            {
                paymentAcceptanceDialog = new PaymentAcceptanceDialog(this,album,orderManager.getCurrentCredit());
                if(paymentAcceptanceDialog.acceptOrder()){
                    orderManager.processDownload();
                }else{
                    Toast.makeText(this, "OOPS!!! SOMETHING IS WRONG \n WITH YOUR NETWORK", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"OOPS!!! YOUR CREDIT IS TOO LOW \n TO BUY THIS ALBUM",Toast.LENGTH_LONG).show();
            }
        }

    }

}


