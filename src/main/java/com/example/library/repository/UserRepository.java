package com.example.library.repository;

import com.example.library.entity.Book;
import com.example.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM user u WHERE u.user_name LIKE %:search%", nativeQuery = true)
    List<User> findAll(String search);

    Page<User> findByRoleList_RoleNameContainingIgnoreCase(String roleName, Pageable pageable);

    Page<User> findByUserNameContainingIgnoreCase(String userName, Pageable pageable);

    Optional<User> findByEmail(String email);
}
