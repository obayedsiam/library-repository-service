package com.example.library.controller;

import com.example.library.entity.Writer;
import com.example.library.request.WriterRequest;
import com.example.library.response.Response;
import com.example.library.service.WriterService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @PostMapping("/save")
    public Response save(@RequestBody WriterRequest request) {
        return writerService.save(request);
    }

    @PutMapping("/update")
    public Response update(@RequestBody Writer writer) {
        return writerService.update(writer);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return writerService.delete(id);
    }

    @GetMapping("/find/{id}")
    public Response findById(@PathVariable Long id) {
        return writerService.findById(id);
    }

    @GetMapping("/getAll")
    public Response getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                           @RequestParam(value = "search", defaultValue = "") String search) {
        return writerService.getAll(search, sortBy);
    }

    @GetMapping("/getList")
    public Response getList(@RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                            @RequestParam(value = "search", defaultValue = "") String search,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return writerService.getList(size, page, sortBy, search);
    }

}
