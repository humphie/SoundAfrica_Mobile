package com.app.user;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.app.Databases.CredentialsRepository;
import com.app.dialogs.RegistrationDialog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */

public class User {
    private AlertDialog.Builder alertDialogBuilder = null;
    private LayoutInflater layoutInflator = null;
    private boolean exist = false;
    View promptView = null;
    private Context context;
    private CredentialsRepository credentialRepository;
    private Access access;
    private Credentials credential;
    private RegistrationDialog registrationDialog;

    public User(Context context)
    {


        // INITIALIZE THE REPOSITORY
        credentialRepository = new CredentialsRepository(context);
        //Check if no user exists

        if(credentialRepository.getAll().isEmpty())
        {   registrationDialog = new RegistrationDialog(context);
            register( registrationDialog.createNewUser());

            access = Access.GRANTED;
        }else{
            List<Credentials> credentialList = credentialRepository.getAll();
            credential = credentialList.get(0);
            access = Access.GRANTED;
        }

    }
    public void register(Credentials credentials){

        int i = credentialRepository.create(credential);
    }


    public Access getAccess()
    {
        return access;
    }
    public Credentials getCredential()
    {
        return credential;
    }
}


