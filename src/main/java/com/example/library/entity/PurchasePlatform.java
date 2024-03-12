package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "PURCHASE_PLATFORM")
public class PurchasePlatform extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_PLATFORM_ID")
    private Long id;

    @Column(name = "PURCHASE_PLATFORM_NAME")
    private String name;

    @OneToMany(mappedBy = "purchasePlatform")
    private List<Book> bookList;
}
