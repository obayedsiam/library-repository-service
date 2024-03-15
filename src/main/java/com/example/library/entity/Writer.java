package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "WRITER")
public class Writer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WRITER_ID")
    private Long id;

    @Column(name = "WRITER_NAME")
    private String writerName;

    @JsonIgnoreProperties("writerList")
    @ManyToMany(mappedBy = "writerList",fetch = FetchType.LAZY)
    private List<Book> bookList;
}
