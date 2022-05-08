package com.example.httpserver.error;

import com.example.httpserver.http.HttpStatusCode;

public class HttpException extends RuntimeException{

    private HttpStatusCode errorCode;

    public HttpException(HttpStatusCode statusCode){
        super(statusCode.MESSAGE);
        this.errorCode = statusCode;
    }

}
