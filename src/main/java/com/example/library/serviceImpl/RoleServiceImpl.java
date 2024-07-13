package com.example.library.serviceImpl;

import com.example.library.entity.Role;
import com.example.library.helper.BookHelper;
import com.example.library.repository.RoleRepository;
import com.example.library.request.RoleRequest;
import com.example.library.response.Response;
import com.example.library.service.RoleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private WriterRepository writerRepository;

    @Autowired
    private BookHelper bookHelper;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Response save(RoleRequest roleRequest) {

        Response response = new Response();

//        if (isValidRoleRequest(roleRequest)) response = bookHelper.save(roleRequest);
//        else {
//            response.setSuccess(false);
//            response.setMessage("Invalid input data");
//        }
        return response;
    }

    @Transactional
    @Override
    public Response update(RoleRequest request) {

        Response response = new Response();

        Optional<Role> role = roleRepository.findById(request.getRoleId());

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

//        Optional<Role> book = bookRepository.findById(id);
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
//        // Create pageable object for pagination and sorting
//        Pageable pageable = PageRequest.of(page, size, Sort.by("bookId").descending());
//
//        // Perform search and retrieve paginated result
//        Page<Book> result;
//
//        if (search != null && !search.isEmpty()) {
//            result = bookRepository.findByBookNameContainingIgnoreCase(search, pageable);
//        } else {
//            result = bookRepository.findAll(pageable);
//        }
//
//        response.setData(result);
//        response.setMessage("Data retrieved successfully");
//        response.setSuccess(true);
        return response;
    }

    private boolean isValidRoleRequest(RoleRequest request) {
        return true;
    }
}