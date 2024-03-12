package com.example.library.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BookRequest {

    private String bookName;
    private Boolean isSingleWriter;
    private Long writerId;
    private Double price;
    private List<Long> writerIds; // Assuming you want to handle multiple writers
    private Date firstPublishedDate;
    private Date purchaseDate;
    private Long purchasedById;
    private Long publisherId;
    private Long purchasePlatformId;

}
