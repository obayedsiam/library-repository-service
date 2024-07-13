package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT b.* FROM book b WHERE b.book_name LIKE %:search%", nativeQuery = true)
    List<Book> findAll(String search);

    Page<Book> findByBookNameContainingIgnoreCase(String bookName, Pageable pageable);
}
