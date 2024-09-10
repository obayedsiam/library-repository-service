package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<Response> save(BookRequest bookRequest);

    ResponseEntity<Response> update(BookRequest bookRequest);

    Response delete(Long id);

    Response findById(Long id);

    Response getAll(String search, String sortBy);

    Response getList(Integer size, Integer page, String sortBy, String search);
}
