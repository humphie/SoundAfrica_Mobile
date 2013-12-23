package com.app.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.app.user.Credentials;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "credentials.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Credentials, String> credentialsDao = null;
    private RuntimeExceptionDao<Credentials, String> runTimeCredentialDao = null;

public DatabaseHelper(Context context){
        super( context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
        try {
            TableUtils.createTable(connectionSource, Credentials.class);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int arg2,
                          int arg3) {

        try {
            TableUtils.dropTable(connectionSource, Credentials.class, true);
        } catch (SQLException e) {


            throw new RuntimeException(e);
        }
        // after we drop the old databases, we create the new ones
        onCreate(db, connectionSource);

    }

    // Returning my Ordinary DAO
    public Dao<Credentials,String> getCredentialDao() throws SQLException
    {
        if(credentialsDao == null){
            credentialsDao = this.getDao(Credentials.class);
        }
        return credentialsDao;
    }
    // Returning a Running Time Exception Version of the Database Object
    RuntimeExceptionDao<Credentials,String> getCredentialDataDoa()
    {
        if(runTimeCredentialDao == null)
        {
            runTimeCredentialDao = this.getRuntimeExceptionDao(Credentials.class);
        }
        return runTimeCredentialDao;
    }
    public void close()
    {

        super.close();
        credentialsDao = null;
    }
}

