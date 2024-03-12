package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ManyToMany(mappedBy = "writerList")
    private Set<Book> bookList;

}
