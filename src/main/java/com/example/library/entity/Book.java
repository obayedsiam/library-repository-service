package com.example.library.entity;

import com.example.library.enums.Language;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "BOOK")
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "IS_SINGLE_WRITER")
    private Boolean isSingleWriter;

    @Column(name = "IS_TRANSLATION")
    private Boolean isTranslation;

    @Column(name = "LANGUAGE")
    private Language language;

//    @ManyToOne
////    @JoinColumn(name = "WRITER_ID")
//    private Writer writer;

    @Column(name = "PRICE")
    private Double price;

//    @Column(name = "TRANSLATOR")
//    private Writer translator;

    @Temporal(TemporalType.DATE)
    @Column(name = "FIRST_PUBLISHED_DATE")
    private Date firstPublishedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "PURCHASED_BY")
    private BookPurchaser purchasedBy;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_PLATFORM")
    private PurchasePlatform purchasePlatform;
}
