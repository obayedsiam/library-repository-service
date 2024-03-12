package com.example.library.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {

    private String message;
    private boolean success;
    private Object data;
}
