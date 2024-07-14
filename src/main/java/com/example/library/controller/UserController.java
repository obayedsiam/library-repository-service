package com.example.library.controller;

import com.example.library.entity.User;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;
import com.example.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v3/api/user")
@Tag(name = "User", description = "User related APIs")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    @Operation(description = "Save user data")
    public Response save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @PutMapping("/update")
    @Operation(description = "Update user data")
    public Response update(@RequestBody UserRequest request) {
        return userService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete user data")
    public Response delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/find/{id}")
    @Operation(description = "Find user by Id")
    public Response findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/getAll")
    @Operation(description = "Get list of all users")
    public Response getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                           @RequestParam(value = "search", defaultValue = "") String search) {
        return userService.getAll(search, sortBy);
    }

    @GetMapping("/getList")
    @Operation(description = "Get paginated list of users")
    public Response getList(@RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                            @RequestParam(value = "search", defaultValue = "") String search,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return userService.getList(size, page, sortBy, search);
    }

//    @GetMapping("/all")
//    public ResponseEntity<String> allUsers() {
////        List<User> users = userService.allUsers();
//
//        return ResponseEntity.ok("Hello World");
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<User> authenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        User currentUser = (User) authentication.getPrincipal();
//
//        return ResponseEntity.ok(currentUser);
//    }

}
