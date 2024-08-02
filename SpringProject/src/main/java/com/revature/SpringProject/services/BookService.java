package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService
{

    public BookRepo br;

    @Autowired
    public BookService(BookRepo br) {
        this.br = br;
    }

    // User story 2: As a user, I can view all Items
    @Override
    public List<Book> findAllBooks()
    {
        return br.findAll();
    }

    public Book findBookById(int id)
    {
        return br.findById(id).orElseGet(null);
    }
    
    // User story 4: As a user, I can update an Item (Change the name or other properties)
    public Book updateBook(Book b) {
        return br.save(b);
    }

    @Override
    public Book addBook(Book book)
    {
        return br.save(book);
    }

    public void deleteBook(int id)
    {
        br.deleteById(id);
    }

}
