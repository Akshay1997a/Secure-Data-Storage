package com.datasecurity.lock.Log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    FileHandler file;

    public void Log() throws SecurityException, IOException {

    }

    public void createLog(String title, String conteString) throws SecurityException, IOException {
        /*file = new FileHandler("C:\\Users\\aksha\\OneDrive\\Desktop\\Log.txt");
        LOGGER.addHandler(file);
        String msg = title + " :: " + conteString;
        LOGGER.log(Level.INFO, msg);*/
    }
}