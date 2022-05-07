package com.example.httpserver.config;

import lombok.Data;

//Ще мапнем този клас към config файла ни
@Data
public class Configuration {

        private int port;
        private String webroot;
}
