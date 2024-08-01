package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService
{
    @Autowired
    public BookRepo br;

    @Override
    public List<Book> findAllBooks()
    {
        return br.findAll();
    }

    public Book findBookById(int id)
    {
        return br.findById(id).get();
    }

    @Override
    public Book addBook(Book book)
    {
        return br.save(book);
    }

}
