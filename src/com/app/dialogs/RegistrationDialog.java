package com.app.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.app.SoundFrica.R;
import com.app.user.Credentials;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */

public class RegistrationDialog {

    private AlertDialog.Builder alertDialogBuilder = null;
    private LayoutInflater layoutInflator = null;
    View promptView = null;
    private Context context;

    public void setCredential(Credentials credential) {
        this.credential = credential;
    }

    private Credentials credential;

    public RegistrationDialog(Context context){

        this.context = context;

    }
    public Credentials createNewUser(){

        layoutInflator = LayoutInflater.from(context);
        promptView = layoutInflator.inflate(R.layout.registry_form, null);
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText firstName = (EditText)promptView.findViewById(R.id.firstName);
        final EditText lastName = (EditText)promptView.findViewById(R.id.lastName);
        final EditText accountName = (EditText)promptView.findViewById(R.id.accountName);
        final EditText mobileNumber = (EditText)promptView.findViewById(R.id.mobile);
        final EditText userName = (EditText)promptView.findViewById(R.id.username);
        final EditText password = (EditText)promptView.findViewById(R.id.password);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int id) {

                        setCredential(new Credentials(firstName.toString(),lastName.toString(),
                                accountName.toString(),userName.toString(),password.toString(),
                                mobileNumber.toString()));

                    }}).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id) {
                setCredential(null);

            }

        });

        AlertDialog alert_D = alertDialogBuilder.create();
        alert_D.show();

        return credential;
    }
}

