package com.example.library.helper;

import com.example.library.entity.Book;
import com.example.library.enums.RecordStatus;
import com.example.library.repository.BookRepository;
import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class BookHelper {

    @Autowired
    private BookRepository bookRepository;

//    @Autowired
//    private WriterRepository writerRepository;

    public Response save(BookRequest bookRequest) {
        Book book = new Book();
        Response response = new Response();
        BeanUtils.copyProperties(bookRequest, book);
        book.setRecordStatus(RecordStatus.ACTIVE);
        bookRepository.save(book);
        response.setSuccess(true);
        response.setMessage("Saved Successfully");
        response.setSize(1);

        return response;
    }

    public Response update(BookRequest request, Book book) {
        BeanUtils.copyProperties(request, book);
        Response response = new Response();
//        List<Writer> writerList = new ArrayList<>();
//
//        for (Long writerId : request.getWriterIds()) {
//            Optional<Writer> writer = writerRepository.findById(writerId);
//            if (writer.isPresent()) writerList.add(writer.get());
//        }
//        book.setWriterList(writerList);
        bookRepository.save(book);
        response.setSuccess(true);
        response.setMessage("Book Updated");
        response.setData(book);
        response.setSize(1);
        return response;
    }

    public Response delete(Book book) {
        Response response = new Response();
        book.setRecordStatus(RecordStatus.DELETED);
        bookRepository.save(book);
        response.setSuccess(true);
        response.setMessage("Book Deleted");
        response.setSize(1);
        return response;
    }

}
