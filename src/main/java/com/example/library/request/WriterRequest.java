package com.example.library.request;

import com.example.library.entity.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class WriterRequest {

    private Long id;
    private String writerName;
    private List<Long> bookIds;
}
