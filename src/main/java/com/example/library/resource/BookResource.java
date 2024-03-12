package com.example.library.resource;

import com.example.library.entity.Book;
import com.example.library.request.BookRequest;
import com.example.library.response.Response;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookResource {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public Response save(@RequestBody BookRequest request) {
        return bookService.save(request);
    }

    @PutMapping("/update")
    public Response update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return bookService.delete(id);
    }

    @GetMapping("/find/{id}")
    public Response findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/getAll")
    public Response getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                       @RequestParam(value = "search", defaultValue = "") String search) {
        return bookService.getAll(search, sortBy);
    }

    @GetMapping("/getList")
    public Response getList(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                            @RequestParam(value = "search", defaultValue = "") String search,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return bookService.getList(size, page, sortBy, search);
    }
}
