package com.example.httpserver.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String target;
    private String httpVersion;
    private Map<String, String> headers;
    private String messageBody;

}
