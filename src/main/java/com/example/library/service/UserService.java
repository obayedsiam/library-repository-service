package com.example.library.service;

import com.example.library.request.BookRequest;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;

public interface UserService {

    Response save(UserRequest userRequest);

    Response update(UserRequest userRequest);

    Response delete(Long id);

    Response findById(Long id);

    Response getAll(String search, String sortBy);

    Response getList(Integer size, Integer page, String sortBy, String search);
}
