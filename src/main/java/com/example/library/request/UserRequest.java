package com.example.library.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserRequest {

    private Long userId;
    private String userName;
    private List<Long> roleIds; // Assuming you want to handle multiple writers

}
