package com.example.library.serviceImpl;

import com.example.library.entity.Book;
import com.example.library.enums.RecordStatus;
import com.example.library.repository.BookRepository;
import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import com.example.library.service.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Response save(BookRequest bookRequest) {

        Response response = new Response();

        if (!isValidBookRequest(bookRequest)) {
            response.setSuccess(false);
            response.setMessage("Invalid input data");
            return response;
        }

        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);

        try {
            bookRepository.save(book);
            response.setData(book);
            response.setSuccess(true);
            response.setMessage("Saved Successfully");

        } catch (DataIntegrityViolationException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());

        }

        return response;
    }

    @Transactional
    @Override
    public Response update(Book book) {

        Response response = findById(book.getBookId());

        if (response.isSuccess() == true) {
            bookRepository.save(book);
            response.setSuccess(true);
            response.setMessage("Book Updated");
            response.setData(book);
        }

        return response;
    }

    @Transactional
    @Override
    public Response delete(Long id) {
        Response response = new Response();
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            b.setRecordStatus(RecordStatus.DELETED);
            bookRepository.save(b);
            response.setSuccess(true);
            response.setMessage("Book Deleted");
        } else {
            response.setSuccess(false);
            response.setMessage("Book Resource Not Found");
        }
        return response;
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response();
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isEmpty()) {
            response.setSuccess(true);
            response.setData(book.get());
        } else {
            response.setSuccess(false);
            response.setMessage("Resource not found with id : " + id);
        }
        return response;
    }

    @Override
    public Response getAll(String search, String sortBy) {

        Response response = new Response();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        // Apply Search Predicate
        Predicate searchPredicate = criteriaBuilder.like(root.get("bookName"), "%" + search + "%");

        Order sortOrder = sortBy.equals("asc") ? criteriaBuilder.asc(root.get("bookId")) : criteriaBuilder.desc(root.get("bookId"));

        criteriaQuery.where(searchPredicate);
        criteriaQuery.orderBy(sortOrder);

        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
        response.setData(typedQuery.getResultList());
        response.setMessage("All Book List");
        response.setSuccess(true);
        return response;
    }

    @Override
    public Response getList(Integer size, Integer page, String sortBy, String search) {
        Response response = new Response();

        // Create pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookId").descending());

        // Perform search and retrieve paginated result
        Page<Book> result;

        if (search != null && !search.isEmpty()) {
            result = bookRepository.findByBookNameContainingIgnoreCase(search, pageable);
        } else {
            result = bookRepository.findAll(pageable);
        }

        response.setData(result);
        response.setMessage("Data retrieved successfully");
        response.setSuccess(true);
        return response;
    }

    private boolean isValidBookRequest(BookRequest request) {
        return true;
    }
}
