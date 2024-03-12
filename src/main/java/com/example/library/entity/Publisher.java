package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "PUBLISHER")
public class Publisher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUBLISHER_ID")
    private Long id;

    @Column(name = "PUBLISHER_NAME")
    private String publisherName;

    @OneToMany (mappedBy = "publisher")
    private List<Book> bookList;
}
