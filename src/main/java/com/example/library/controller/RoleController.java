package com.example.library.controller;

import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import com.example.library.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

//    @PostMapping("/save")
//    public Response save(@RequestBody BookRequest request) {
//        return roleService.save(request);
//    }
//
//    @PutMapping("/update")
//    public Response update(@RequestBody BookRequest bookRequest) {
//        return roleService.update(bookRequest);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public Response delete(@PathVariable Long id) {
//        return roleService.delete(id);
//    }
//
//    @GetMapping("/find/{id}")
//    public Response findById(@PathVariable Long id) {
//        return roleService.findById(id);
//    }
//
//    @GetMapping("/getAll")
//    public Response getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
//                           @RequestParam(value = "search", defaultValue = "") String search) {
//        return roleService.getAll(search, sortBy);
//    }
//
//    @GetMapping("/getList")
//    public Response getList(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
//                            @RequestParam(value = "search", defaultValue = "") String search,
//                            @RequestParam(value = "page", defaultValue = "0") Integer page,
//                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
//        return roleService.getList(size, page, sortBy, search);
//    }
}