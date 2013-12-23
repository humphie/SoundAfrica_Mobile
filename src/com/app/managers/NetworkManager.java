package com.app.managers;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */


/********************************************************************/
/*  THIS CLASS HANDLES ALL NETWORK CONNECTIONS AND RESOLUTIONS      */
/********************************************************************/
/********************************************************************/
/* SINCE ALL SEEMS TO BE IN POST FORM , I GUESS HTTP POST WILL WORK */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/********************************************************************/
public class NetworkManager {

    private String urlPath;
    private URL url;

    private HttpURLConnection connection;
    private Status status;



    public NetworkManager()
    {

    }
    public void sessionCreate() throws IOException {
        // Jokes, just jokes man
    }
    private void engageRequest(){
        // Well, it was an option for the future i suppose
    }
    public void connectToUrl(String urlPath)
    {
        setURL(urlPath);
        try {
            connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("context-type","application/x-protobuf"); // Tell dem say [Yah! know di ting]
            status = Status.CONNECTION_OK;
        } catch (IOException e) {
            e.printStackTrace(); // Rudende Farted
            status = Status.CONNECTION_DOWN;
        }
    }
    private void setURL(String url){
        try {
            this.url = new URL(url); // Kusimma
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Broke a pipe // Mega Flood of wrong
        }

    }



    public InputStream getInputStream() throws IOException {

        connection.setDoInput(true);
        return connection.getInputStream();

    }
    public OutputStream getOutputStream() throws IOException {

        connection.setDoOutput(true);
        return connection.getOutputStream() ;

    }
    public void closeConnection(){

    }
    public NetworkManager getManager(){
        return this;
    }
    public String fileName(){

        return  urlPath.substring( urlPath.lastIndexOf('/')+1, urlPath.length() );
    }
    public long fileSize(){

        return connection.getContentLength();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

