package com.app.user;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */

public enum Access {


    GRANTED("GRANTED"),DENIED("DENIED");
    private String access;

    private Access(String access){
        this.access = access;
    }

    public String getAccess()
    {
        return access;

    }

}
