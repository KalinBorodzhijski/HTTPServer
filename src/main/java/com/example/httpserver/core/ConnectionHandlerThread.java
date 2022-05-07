package com.example.httpserver.core;

import com.example.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandlerThread extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    private Socket socket;

    public  ConnectionHandlerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            LOGGER.info("Connection processing finished");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null) inputStream.close();
                if(outputStream != null) outputStream.close();
                if(socket != null) socket.close();
            } catch (IOException ignored) {}
        }

    }
}
