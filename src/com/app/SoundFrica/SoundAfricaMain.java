package com.app.SoundFrica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import com.app.managerUIs.CollectionManagerUI;
import com.app.user.Access;
import com.app.user.User;

public class SoundAfricaMain extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Intent prevIntent = getIntent();
    private String result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        User appUser = new User(this);

            try{

                result = (String)prevIntent.getSerializableExtra("failed-start");
                if(result.equals("failed-start"))
                {
                    Toast.makeText(this,"OOPS!!! NETWORK DOWN",Toast.LENGTH_LONG).show();
                }

            }catch (NullPointerException exception)
            {
                if(appUser.getAccess().equals(Access.GRANTED))
                {

                    Intent intent = new Intent(SoundAfricaMain.this,CollectionManagerUI.class);
                    startActivity(intent);
                    this.finish();
                }

            }






            Toast.makeText(this, "OOPS!!! NETWORK DOWN", Toast.LENGTH_LONG).show();


    }


}
