package com.example.library.controller;

import com.example.library.request.BookRequest;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Response save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @PutMapping("/update")
    public Response update(@RequestBody UserRequest request) {
        return userService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/find/{id}")
    public Response findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/getAll")
    public Response getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                           @RequestParam(value = "search", defaultValue = "") String search) {
        return userService.getAll(search, sortBy);
    }

    @GetMapping("/getList")
    public Response getList(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                            @RequestParam(value = "search", defaultValue = "") String search,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return userService.getList(size, page, sortBy, search);
    }
}
