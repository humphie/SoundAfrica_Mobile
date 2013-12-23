package com.app.managers;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */

public enum ServerReply {

    GOT_FILE("GOT_FILE"),RESUME_DOWNLOAD("RESUME_DOWNLOAD"),FINISHED_DOWNLOAD("FINISHED_DOWNLOAD");

    private String info;

    private ServerReply(String info)
    {this.setInfo(info);}

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

