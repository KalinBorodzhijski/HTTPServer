package com.example.httpserver.config;

import com.example.httpserver.util.Json;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager manager;
    private static Configuration configuration;

    private ConfigurationManager(){}

    public static ConfigurationManager getInstance(){
        if(manager == null){
            manager = new ConfigurationManager();
        }
        return manager;
    }

    public void loadConfigFile(String path) {
        int i;
        FileReader reader = null;
        try {
            reader = new FileReader(path);
            StringBuffer buffer = new StringBuffer();
            while((i = reader.read()) != -1){
                buffer.append((char) i);
            }
            JsonNode conf = Json.parse(buffer.toString());
            configuration = Json.fromJson(conf, Configuration.class);
        } catch (IOException e) {
            throw new HttpConfigurationException("Error loading configuration");
        }
    }


    public Configuration getCurrentConfig(){
        if(configuration == null){
            throw new HttpConfigurationException("Config file is not loaded");
        }
        return configuration;
    }
}
