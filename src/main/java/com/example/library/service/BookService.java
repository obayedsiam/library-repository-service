package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.request.BookRequest;
import com.example.library.response.Response;

import java.util.List;

public interface BookService {

    Response save(BookRequest bookRequest);

    Response update(BookRequest bookRequest);

    Response delete(Long id);

    Response findById(Long id);

    Response getAll(String search, String sortBy);

    Response getList(Integer size, Integer page, String sortBy, String search);
}
