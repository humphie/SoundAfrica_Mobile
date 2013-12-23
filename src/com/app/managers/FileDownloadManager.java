package com.app.managers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import com.app.utilities.FileUnzipper;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */


public class FileDownloadManager {
    private ProgressDialog progressbar = null;
    private long fileSize;

    private InputStream input = null;
    private FileOutputStream fileOut = null;
    private String fileName;
    private String urlPath;
    private FileUnzipper unzipper;
    private NetworkManager networkManager;
    private int count;
    private byte[] data;
    private int total;
    private File actualFile;
    public FileDownloadManager(Context context, String archivePath,NetworkManager networkManager) {


        progressbar = new ProgressDialog(context);
        progressbar.setCancelable(true);
        progressbar.setMessage("File Downloading...");
        progressbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressbar.setProgress(0);
        progressbar.setMax(100);
        this.urlPath = archivePath;
        data  = new byte[1024];
        this.networkManager = networkManager;
    }
    public void startDownload() {
        progressbar.show();

        new Thread(new Runnable(){
            @Override
            public void run() {

                fileSize = networkManager.fileSize();
                try {



                    fileOut = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + fileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    input = networkManager.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    while((count = input.read(data, 0, 1024)) != -1)
                    {
                        try {
                            fileOut.write(data, 0, count);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        total += count;
                        progressbar.setProgress((int) ((total*100)/fileSize));
                        // Download Progress Bar Update Module picked up at this point
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                progressbar.dismiss();

            }}).start();

    }

    public void extract() throws IOException {
        String  inputFile = (Environment.getExternalStorageDirectory() + "/" + fileName);
        String  outputDirectory = (Environment.getExternalStorageDirectory() + "/Music/");
        unzipper = new FileUnzipper(inputFile,outputDirectory);
        unzipper.decompress();
    }
}

