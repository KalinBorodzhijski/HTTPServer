package com.example.httpserver.http;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HttpStatusCode {
    BAD_REQUEST_400(400,"Bad Request"),
    METHOD_NOT_ALLOWED_401(401,"Method not allowed"),
    BAD_REQUEST_414(414,"URI too long"),
    INTERNAL_SERVER_ERROR_500(500, "Internal server error"),
    NOT_IMPLEMENTED_501(501, "Not implemented");

    public final int STATUS_CODE;
    public final String MESSAGE;
}
