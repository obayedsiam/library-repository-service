package com.example.library.service;


import com.example.library.entity.Writer;
import com.example.library.request.WriterRequest;
import com.example.library.response.Response;

public interface WriterService {

    Response save(WriterRequest writerRequest);

    Response update(Writer writer);

    Response delete(Long id);

    Response findById(Long id);

    Response getAll(String search, String sortBy);

    Response getList(Integer size, Integer page, String sortBy, String search);
}
