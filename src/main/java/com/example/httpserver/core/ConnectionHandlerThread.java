package com.example.httpserver.core;

import com.example.httpserver.http.HttpParser;
import com.example.httpserver.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionHandlerThread extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionHandlerThread.class);
    private Socket socket;

    public  ConnectionHandlerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        int byteInput;

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            LOGGER.info("Connection processing finished");
/*
            while((byteInput = inputStream.read()) >= 0){
                System.out.print((char) byteInput);
            }
*/

            System.out.println(HttpParser.parseHttpRequest(inputStream));


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
