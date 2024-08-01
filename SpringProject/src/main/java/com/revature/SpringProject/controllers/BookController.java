package com.revature.SpringProject.controllers;

import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Autowired
    BookService bs;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        return new ResponseEntity<>(bs.addBook(book), HttpStatus.OK); // Book inserted
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bs.findAllBooks();
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bs.updateBook(book);
    }


}
