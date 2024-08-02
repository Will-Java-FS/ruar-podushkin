package com.revature.SpringProject.controllers;

import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.services.BookService;

import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;

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
        if(book != null)
            return new ResponseEntity<>(bs.addBook(book), HttpStatus.OK); // Book inserted

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Bad object
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id)
    {
        Book foundBook = bs.findBookById(id);

        if(foundBook != null)
            return new ResponseEntity<>(foundBook, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id)
    {
        bs.deleteBook(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
  
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bs.findAllBooks(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book>  updateBook(@RequestBody Book book) {
        Book b = bs.updateBook(book);
        if (b == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(bs.updateBook(book), HttpStatus.OK);
        }
    }
}
