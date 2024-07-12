package com.example.library.controller;

import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import com.example.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("v3/api/book")
@RestController
@Tag(name = "Book", description = "Book related API's")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    @Operation(description = "Adding Book")
    public Response save(@RequestBody BookRequest request) {
        return bookService.save(request);
    }

    @PutMapping("/update")
    @Operation(description = "Updating Book")
    public Response update(@RequestBody BookRequest request) {
        return bookService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Deleting Book")
    public Response delete(@PathVariable Long id) {
        return bookService.delete(id);
    }

    @GetMapping("/find/{id}")
    @Operation(description = "Finding Book")
    public Response findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/getAll")
    @Operation(description = "Getting All Book")
    public Response getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                           @RequestParam(value = "search", defaultValue = "") String search) {
        return bookService.getAll(search, sortBy);
    }

    @GetMapping("/getList")
    @Operation(description = "Getting Paginated Book")
    public Response getList(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                            @RequestParam(value = "search", defaultValue = "") String search,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return bookService.getList(size, page, sortBy, search);
    }
}
