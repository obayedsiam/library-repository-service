package com.example.library.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class Response {

    private String message;
    private boolean success;
    private int size;
    private Object data;
}
