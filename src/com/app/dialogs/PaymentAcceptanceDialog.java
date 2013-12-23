package com.app.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.app.SoundFrica.R;
import com.app.protobuffers.Databuffers;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class PaymentAcceptanceDialog {
    private AlertDialog.Builder alertDialogBuilder = null;
    private LayoutInflater layoutInflator = null;
    private EditText albumName;
    private EditText albumPrice;
    private ImageView coverArt;
    private Button button;
    private Databuffers.Album album;
    private int credit;
    View promptView = null;
    private Context context;
    private boolean acceptance = false;

    public PaymentAcceptanceDialog(Context context,Databuffers.Album album,int credit)
    {
        this.context = context;
        this.album = album;
        this.credit = credit;


    }
    public boolean acceptOrder(){
        layoutInflator = LayoutInflater.from(context);
        promptView = layoutInflator.inflate(R.layout.payment_acceptance_dialog, null);
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText currentCredit = (EditText)promptView.findViewById(R.id.currentCredit);
        final EditText albumPrice = (EditText)promptView.findViewById(R.id.albumPrice);
        Button finalOrder = (Button)promptView.findViewById(R.id.finalOrder);

        currentCredit.setText(credit);
        albumPrice.setText(album.getPrice());

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Order", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int id) {

                        // Do the Order here
                        System.out.println("ORDER MADE");
                        acceptance = true;


                    }}).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id) {
                // SUICIDE KILL PROGRAM HERE ,COMMIT SEMPUKKU [HARAKIRI]

                // Revert back to other activity and close
                acceptance = false;

            }

        });


        return acceptance;
    }
}
