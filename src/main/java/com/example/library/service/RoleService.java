package com.example.library.service;

import com.example.library.request.RoleRequest;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;

public interface RoleService {

    Response save(RoleRequest roleRequest);

    Response update(RoleRequest roleRequest);

    Response delete(Long id);

    Response findById(Long id);

    Response getAll(String search, String sortBy);

    Response getList(Integer size, Integer page, String sortBy, String search);
}
