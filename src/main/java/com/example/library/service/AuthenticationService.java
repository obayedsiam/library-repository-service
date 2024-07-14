package com.example.library.service;

import com.example.library.dto.LoginUserDto;
import com.example.library.dto.RegisterUserDto;
import com.example.library.entity.Role;
import com.example.library.entity.User;
import com.example.library.enums.RoleEnum;
import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByRole(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input){
        User user = userRepository.findByEmail(input.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), input.getPassword()));
        return user;
    }
}
