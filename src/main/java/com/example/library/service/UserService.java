package com.example.library.service;

import com.example.library.dto.RegisterUserDto;
import com.example.library.entity.User;
import com.example.library.request.BookRequest;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;

import java.util.List;

public interface UserService {

    Response save(UserRequest userRequest);

    Response update(UserRequest userRequest);

    Response delete(Long id);

    Response findById(Long id);

    List<User> allUsers();

    Response getAll(String search, String sortBy);

    Response getList(Integer size, Integer page, String sortBy, String search);

    User createAdministrator(RegisterUserDto input);
}
