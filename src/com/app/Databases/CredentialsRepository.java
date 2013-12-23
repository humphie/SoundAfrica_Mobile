package com.app.Databases;

import android.content.Context;
import com.app.user.Credentials;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CredentialsRepository {

    private DatabaseHelper db;
    Dao<Credentials,String> credentialDao;

    public CredentialsRepository(Context context)
    {
        try{
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(context);
            credentialDao = db.getCredentialDao();
        }
        catch(SQLException sql_e){
            sql_e.printStackTrace();
        }
    }
    public int create(Credentials credentials)
    {
        try{
            return credentialDao.create(credentials);
        }catch(SQLException sql_e){
            sql_e.printStackTrace();
        }
        return 0;
    }
    public int update(Credentials credentials)
    {
        try {
            return credentialDao.update(credentials);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return 0;

    }
    public int delete(Credentials credentials)
    {
        try {
            return credentialDao.delete(credentials);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return 0;
    }
    public List<Credentials> getAll()
    {

        try {
            return credentialDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Credentials getCredentials() throws SQLException {
        List<Credentials> tempCredential = credentialDao.queryForAll();

        return tempCredential.get(0);

    }


}
