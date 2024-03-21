package com.example.library.serviceImpl;

import com.example.library.entity.Book;
import com.example.library.repository.UserRepository;
import com.example.library.request.BookRequest;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;
import com.example.library.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Response save(UserRequest userRequest) {

        Response response = new Response();
//
//        if (isValidBookRequest(userRequest)) response = bookHelper.save(bookRequest);
//        else {
//            response.setSuccess(false);
//            response.setMessage("Invalid input data");
//        }
        return response;
    }

    @Transactional
    @Override
    public Response update(UserRequest request) {

        Response response = new Response();

//        Optional<Book> book = userRepository.findById(request.getBookId());
//
//        if (book.isPresent()) bookHelper.update(request, book.get());
//
//        else {
//            response.setSuccess(false);
//            response.setMessage("Book Not Found");
//        }
        return response;
    }

    @Transactional
    @Override
    public Response delete(Long id) {

        Response response = new Response();
//
//        Optional<Book> book = bookRepository.findById(id);
//
//        if (book.isPresent()) {
//          response = bookHelper.delete(book.get());
//        } else {
//            response.setSuccess(false);
//            response.setMessage("Book resource not found");
//        }

        return response;
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response();
//        Optional<Book> book = bookRepository.findById(id);
//        if (book.isPresent()) {
//            response.setSuccess(true);
//            response.setData(book.get());
//        } else {
//            response.setSuccess(false);
//            response.setMessage("Resource not found");
//        }
        return response;
    }

    @Override
    public Response getAll(String search, String sortBy) {

        Response response = new Response();

//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = criteriaQuery.from(Book.class);
//
//        // Apply Search Predicate
//        Predicate searchPredicate = criteriaBuilder.like(root.get("bookName"), "%" + search + "%");
//
//        Order sortOrder = sortBy.equals("asc") ? criteriaBuilder.asc(root.get("bookId")) : criteriaBuilder.desc(root.get("bookId"));
//
//        criteriaQuery.where(searchPredicate);
//        criteriaQuery.orderBy(sortOrder);
//
//        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
//
//        response.setData(typedQuery.getResultList());
//        response.setMessage("All Book List");
//        response.setSuccess(true);

        return response;
    }

    @Override
    public Response getList(Integer size, Integer page, String sortBy, String search) {
        Response response = new Response();
        // Create pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookId").descending());

        // Perform search and retrieve paginated result
        Page<Book> result;
//
//        if (search != null && !search.isEmpty()) {
//            result = bookRepository.findByBookNameContainingIgnoreCase(search, pageable);
//        } else {
//            result = bookRepository.findAll(pageable);
//        }

//        response.setData(result);
        response.setMessage("Data retrieved successfully");
        response.setSuccess(true);
        return response;
    }

    private boolean isValidBookRequest(BookRequest request) {
        return true;
    }
}
