package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Book;

import java.util.List;

public interface IBookService
{
    public List<Book> findAllBooks();
    public Book addBook(Book book);
}
