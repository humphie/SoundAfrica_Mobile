package com.app.utilities;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 12/18/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */

public class FileUnzipper {

    private Archiver archiver = null;
    private String sSource = null;
    private String sDestination = null;

    public FileUnzipper(String source,String destination){

        this.sSource = source;
        this.sDestination = destination;

    }
    public boolean decompress() throws IOException
    {
        archiver = ArchiverFactory.createArchiver("tar", "gz");
        archiver.extract(new File(sSource),new File(sDestination));
        return true;
    }
}

