package com.example.library.request;

import com.example.library.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserRequest {

    private Long id;
    private String userName;
    private String password;
    private String phone;
    private String email;

    public User requestToEntity(){
        User user = new User();
        BeanUtils.copyProperties(this,user);
        return user;
    }

}
