package com.blog.response;

import org.springframework.http.HttpStatusCode;

public interface Response {
    HttpStatusCode getStatus();
    String getMessage();
    Object getData();
}
