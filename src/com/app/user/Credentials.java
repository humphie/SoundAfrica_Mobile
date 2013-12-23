package com.app.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */


@DatabaseTable(tableName = "Credentials")
public class Credentials {
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @DatabaseField(canBeNull = false)
    private String firstName;
    @DatabaseField(canBeNull = false)
    private String lastName;
    @DatabaseField(canBeNull = false)
    private String accountName;
    @DatabaseField(canBeNull = false)
    private String userName;
    @DatabaseField(canBeNull = false)
    private String password;
    @DatabaseField(canBeNull = false)
    private String phoneNumber;

    public Credentials()
    {

    }
    public Credentials(String firstName,String lastName,String accountName,String userName,String password,String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountName = accountName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;

    }
    public String getAccountName() {
        return accountName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }



}

