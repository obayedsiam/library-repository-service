package com.example.library.repository;

import com.example.library.entity.Role;
import com.example.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r.* FROM role u WHERE r.role_name LIKE %:search%", nativeQuery = true)
    List<User> findAll(String search);

    Optional<Role> findByRoleName(String roleName);

    Page<User> findByRoleNameContainingIgnoreCase(String roleName, Pageable pageable);
}
