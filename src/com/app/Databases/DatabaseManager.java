package com.app.Databases;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseManager {
    private DatabaseHelper databaseHelper = null;

    public DatabaseHelper getHelper(Context context)
    {
        if(databaseHelper == null)
        {

            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;

    }
    public void releaseHelper(DatabaseHelper helper)
    {
        if(databaseHelper != null)
        {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}