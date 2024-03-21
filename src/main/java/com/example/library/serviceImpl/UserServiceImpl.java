package com.example.library.serviceImpl;

import com.example.library.entity.User;
import com.example.library.enums.RecordStatus;
import com.example.library.repository.UserRepository;
import com.example.library.request.BookRequest;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;
import com.example.library.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        User user = userRequest.requestToEntity();
        user = userRepository.save(user);
        Response response = new Response();
        response.setSuccess(true);
        response.setMessage("Saved successfully");
        response.setSize(1);
        response.setData(user);
        return response;
    }

    @Transactional
    @Override
    public Response update(UserRequest request) {

        Response response = new Response();

        Optional<User> userOptional = userRepository.findById(request.getId());

        if (userOptional.isPresent()) {
            User user  = userOptional.get();
            BeanUtils.copyProperties(request,user);
            user = userRepository.save(user);
            response.setData(user);
            response.setMessage("User updated");
            response.setSuccess(true);
            response.setSize(1);
        } else {
            response.setSuccess(false);
            response.setMessage("Book Not Found");
        }
        return response;
    }

    @Transactional
    @Override
    public Response delete(Long id) {

        Response response = new Response();

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            user.get().setRecordStatus(RecordStatus.DELETED);
            userRepository.save(user.get());
        } else {
            response.setSuccess(false);
            response.setMessage("Book resource not found");
        }

        return response;
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            response.setSuccess(true);
            response.setData(user.get());
        } else {
            response.setSuccess(false);
            response.setMessage("Resource not found");
        }
        return response;
    }

    @Override
    public Response getAll(String search, String sortBy) {

        Response response = new Response();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        // Apply Search Predicate
        Predicate searchPredicate = criteriaBuilder.like(root.get("userName"), "%" + search + "%");

        Order sortOrder = sortBy.equals("asc") ? criteriaBuilder.asc(root.get("id")) : criteriaBuilder.desc(root.get("id"));

        criteriaQuery.where(searchPredicate);
        criteriaQuery.orderBy(sortOrder);

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        response.setData(typedQuery.getResultList());
        response.setMessage("All User List");
        response.setSuccess(true);

        return response;
    }

    @Override
    public Response getList(Integer size, Integer page, String sortBy, String search) {
        Response response = new Response();
        // Create pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        // Perform search and retrieve paginated result
        Page<User> result;
        if (search != null && !search.isEmpty()) {
            result = userRepository.findByUserNameContainingIgnoreCase(search, pageable);
        } else {
            result = userRepository.findAll(pageable);
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
