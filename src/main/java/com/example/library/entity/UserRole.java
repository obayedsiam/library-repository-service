package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USER_ROLE")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private Role role;
}
