package com.example.library.repository;

import com.example.library.entity.Book;
import com.example.library.entity.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {

    @Query(value = "SELECT w.* FROM writer w WHERE w.writer_name LIKE %:search%", nativeQuery = true)
    List<Writer> findAll(String search);

    Page<Writer> findByWriterNameContainingIgnoreCase(String bookName, Pageable pageable);
}
