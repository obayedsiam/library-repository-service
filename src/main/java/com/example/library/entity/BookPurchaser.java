package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "BOOK_PURCHASER")
public class BookPurchaser extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_PURCHASER_ID")
    private Long id;

    @Column(name = "PURCHASER_NAME")
    private String purchaserName;

    @OneToMany(mappedBy = "purchasedBy")
    private List<Book> bookList;
}
