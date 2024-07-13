package com.example.library.request;

import com.example.library.entity.Role;
import com.example.library.entity.User;
import com.example.library.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class UserRequest {

    private Long id;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private List<Role> roleList;

    public User requestToEntity(){
        User user = new User();
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setEmail(this.email);
//        user.setRoleList(this.roleList);
        return user;
    }

}
