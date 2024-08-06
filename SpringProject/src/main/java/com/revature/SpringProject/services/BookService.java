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

    // User story 3 As a user, I can view Item by its ID
    public Book findBookById(int id)
    {
        return br.findById(id).orElse(null);
    }
    
    // User story 4: As a user, I can update an Item (Change the name or other properties)
    public Book updateBook(Book b) {
        if (!isValidBook(b)) {
            return null;
        } else {
            return br.save(b);
        }
    }

    // User story 1: As a user, I can create a new Item
    @Override
    public Book addBook(Book book)
    {
        return br.save(book);
    }

    // User story 5: As a user, I can delete an Item
    public void deleteBook(int id)
    {
        br.deleteById(id);
    }

    // Local method to check if a book is valid or not
    private boolean isValidBook(Book b) {
        return (b != null && b.getTitle() != null && b.getAuthor() != null);
    }

}
