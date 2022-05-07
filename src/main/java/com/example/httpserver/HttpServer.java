package com.example.httpserver;

import com.example.httpserver.config.Configuration;
import com.example.httpserver.config.ConfigurationManager;
import com.example.httpserver.core.ConnectionHandlerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args){
        LOGGER.info("Starting server...");

        ConfigurationManager.getInstance().loadConfigFile("src/main/resources/config.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfig();
        ServerSocket serverSocket = null;
        try {

            serverSocket = new ServerSocket(configuration.getPort());
            while (serverSocket.isBound() && !serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                LOGGER.info("Accepted connection: " + socket.getInetAddress());
                ConnectionHandlerThread connectionHandlerThread = new ConnectionHandlerThread(socket);
                connectionHandlerThread.start();

            }
        } catch (IOException e) {
            LOGGER.error("Error accepting connection");
            e.printStackTrace();
        }finally {
            if(serverSocket != null)
                try{
                    serverSocket.close();
                }catch (IOException ignored){}
        }

    }
}
