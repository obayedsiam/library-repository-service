package com.example.library.helper;

import com.example.library.entity.Book;
import com.example.library.entity.Writer;
import com.example.library.enums.RecordStatus;
import com.example.library.repository.BookRepository;
import com.example.library.repository.WriterRepository;
import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Component
@RequiredArgsConstructor
public class BookHelper {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private Response response;

    public Response save(BookRequest bookRequest) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);

        List<Writer> writerList = new ArrayList<>();

        for(Long writerId : bookRequest.getWriterIds()){
            Optional<Writer> writer = writerRepository.findById(writerId);
            if(writer.isPresent()) writerList.add(writer.get());
        }

        book.setWriterList(writerList);
        book.setRecordStatus(RecordStatus.ACTIVE);
        bookRepository.save(book);
        response.setData(book);
        response.setSuccess(true);
        response.setMessage("Saved Successfully");

        return response;
    }

    public Response update(BookRequest request, Book book) {
        BeanUtils.copyProperties(request, book);
        List<Writer> writerList = new ArrayList<>();

        for (Long writerId : request.getWriterIds()) {
            Optional<Writer> writer = writerRepository.findById(writerId);
            if (writer.isPresent()) writerList.add(writer.get());
        }
        book.setWriterList(writerList);
        bookRepository.save(book);
        response.setSuccess(true);
        response.setMessage("Book Updated");
        response.setData(book);
        return response;
    }

    public Response delete(Book book){
        Book b = book;
        b.setRecordStatus(RecordStatus.DELETED);
        bookRepository.save(b);
        response.setSuccess(true);
        response.setMessage("Book Deleted");
        return response;
        }

}
