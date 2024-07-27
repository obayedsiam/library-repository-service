package com.example.library.serviceImpl;

import com.example.library.dto.RegisterUserDto;
import com.example.library.entity.Book;
import com.example.library.entity.Role;
import com.example.library.entity.User;
import com.example.library.entity.UserRole;
import com.example.library.enums.RecordStatus;
import com.example.library.enums.RoleEnum;
import com.example.library.repository.RoleRepository;
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
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Response save(UserRequest userRequest) {
        User user = userRequest.requestToEntity();

        // Save unsaved Role entities
        Set<Role> roles = new HashSet<>();
        for (Role role : userRequest.getRoleList()) {
            if (role.getId() == null || role.getId() == 0) {
                // If the Role entity has no ID (i.e., it's unsaved), save it
                Role savedRole = roleRepository.save(role);
                roles.add(savedRole);
            } else {
                // If the Role entity has an ID, assume it's already saved
                role = roleRepository.findById(role.getId()).get();
                roles.add(role);
            }
        }
        // Associate the fetched Role entities with the User
//        user.setRoleList(roles);

        // Save the User entity
        user = userRepository.save(user);

//        user = userRepository.save(user);
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
            User user = userOptional.get();
            BeanUtils.copyProperties(request, user);
            user = userRepository.save(user);
            response.setData(user);
            response.setMessage("User updated");
            response.setSuccess(true);
            response.setSize(1);
        } else {
            response.setSuccess(false);
            response.setMessage("User Not Found");
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

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public Response getAll(String search, String sortBy) {

        Response response = new Response();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Join<User, Role> roleJoin = root.join("roleList", JoinType.INNER);
        // Apply Search Predicate
        Predicate searchPredicate = criteriaBuilder.or(criteriaBuilder.like(root.get("userName"), "%" + search + "%"),
                criteriaBuilder.like(roleJoin.get("roleName"), "%"+ search +"%"));



        Order sortOrder = sortBy.equals("asc") ? criteriaBuilder.asc(root.get("id")) : criteriaBuilder.desc(root.get("id"));

        criteriaQuery.where(searchPredicate);
        criteriaQuery.orderBy(sortOrder);

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        response.setData(typedQuery.getResultList());
        response.setMessage("All User List");
        response.setSize(typedQuery.getResultList().size());
        response.setSuccess(true);

        return response;
    }

    @Override
    public Response getList(Integer size, Integer page, String sortBy, String search) {
        Response response = new Response();
        Pageable pageable = PageRequest.of(page, size, Sort.by("userName").descending());

        // Perform search and retrieve paginated result
        Page<User> result;

        if (search != null && !search.isEmpty()) {
            result = userRepository.findByNameContainingIgnoreCase(search, pageable);
            if (result.isEmpty()) {
                result = userRepository.findByRoleRoleNameContainingIgnoreCase(search, pageable);
            }
        } else {
            result = userRepository.findAll(pageable);
        }
        response.setData(result);
        response.setMessage("Data retrieved successfully");
        response.setSuccess(true);
        response.setSize(result.getSize());
        return response;
    }

    private boolean isValidBookRequest(BookRequest request) {
        return true;
    }


    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByRole(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User()
                .setName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }
}
