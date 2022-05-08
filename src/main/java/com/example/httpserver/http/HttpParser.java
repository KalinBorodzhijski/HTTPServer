package com.example.httpserver.http;

import com.example.httpserver.error.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private static final int CR = 13;
    private static final int LF = 10;
    /*
        Http request contains 3 parts:
        1. Start line - method <SPACE> request-target <SPACE> Http_version <CRLF>
        2. Header fields - not mandatory
        3. Message body
     */
    public static HttpRequest parseHttpRequest(InputStream inputStream){
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        BufferedReader bufferedReader = new BufferedReader(reader);
        HttpRequest request = new HttpRequest();

        try {
            parseStartLine(bufferedReader,request);
            parseHeader(bufferedReader,request);
            parseBody(bufferedReader,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }


    private static void parseHeader(BufferedReader reader, HttpRequest request) throws IOException {
        List<String> stringList = new ArrayList<>();
        Map<String,String> requestHeaders = new HashMap<>();
        String line = reader.readLine();
        while(line.length() > 0){
            stringList.add(line);
            line = reader.readLine();
        }

        for (String header:stringList) {
            int idx = header.indexOf(":");
            if (idx == -1) {
                throw new HttpException(HttpStatusCode.BAD_REQUEST_400);
            }
            requestHeaders.put(header.substring(0, idx), header.substring(idx + 1));
        }
        request.setHeaders(requestHeaders);

    }
    private static void parseBody(BufferedReader reader, HttpRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String bodyLine = reader.readLine();
        while (bodyLine != null) {
            stringBuilder.append(bodyLine);
            bodyLine = reader.readLine();
        }
        request.setMessageBody(stringBuilder.toString());
    }

    private static void parseStartLine(BufferedReader reader, HttpRequest request) throws IOException {
        String inputLine = reader.readLine();

        List<String> stringList = List.of(inputLine.split(" "));
        if(stringList.size() == 3){
            request.setMethod(HttpMethod.valueOf(stringList.get(0)));
            request.setTarget(stringList.get(1));
            request.setHttpVersion(stringList.get(2));
        }
        else throw new HttpException(HttpStatusCode.BAD_REQUEST_400);

    }

}
