package com.app.managers;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Status {

    CONNECTION_OK(200),CONNECTION_DOWN(404),CONNECTION_FAILED(500);

    private int status;
    private Status(int status)
    {
           this.status = status;

    }
    public int getStatus()
    {
        return status;
    }
}
