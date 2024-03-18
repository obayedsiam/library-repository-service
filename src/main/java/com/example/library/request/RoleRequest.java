package com.example.library.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class RoleRequest {

    private Long roleId;
    private String roleName;
    private List<Long> userIds; // Assuming you want to handle multiple writers


}
