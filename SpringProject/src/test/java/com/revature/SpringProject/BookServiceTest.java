package com.revature.SpringProject;


import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.repositories.BookRepo;
import com.revature.SpringProject.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllBooks() {
        List<Book> expectedBooks = new ArrayList<>();

        expectedBooks.add(new Book(1, "title1", "someAuthor1",
                123, "Fantasy", 1, new Account(), 1));

        expectedBooks.add(new Book(2, "title2", "someAuthor2",
                321, "Mystery", 2, new Account(), 2));

        when(bookRepo.findAll()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.findAllBooks();
        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void testFindBookByExistingId() {
        Book expectedBook = new Book();
        when(bookRepo.findById(1)).thenReturn(Optional.of(expectedBook));

        Book actualBook = bookService.findBookById(1);

        assertEquals(expectedBook, actualBook);
        verify(bookRepo).findById(1); // Make sure findById() happened
    }

    @Test
    void testFindBookByNonexistingId() {
        Book expectedBook = new Book();
        when(bookRepo.findById(1)).thenReturn(Optional.empty());

        Book actualBook = bookService.findBookById(1);

        assertNull(actualBook);
        verify(bookRepo).findById(1); // Make sure findById() happened
    }

    @Test
    void testUpdateBook_ValidBook() {
        Book bookToUpdate = new Book(1, "title1", "someAuthor1",
                123, "Fantasy", 1, new Account(),1);
        when(bookRepo.save(bookToUpdate)).thenReturn(bookToUpdate);

        Book updatedBook = bookService.updateBook(bookToUpdate);

        assertEquals(bookToUpdate, updatedBook);
        verify(bookRepo).save(bookToUpdate);
    }

    @Test
    void testUpdateBook_InvalidBook() {
        Book invalidBook = new Book();

        Book updatedBook = bookService.updateBook(invalidBook);

        assertNull(updatedBook);
        verify(bookRepo, never()).save(any());  // Make sure save() was not called in this case
    }

    @Test
    void testAddBook() {
        Book bookToAdd = new Book(1, "title1", "someAuthor1",
                123, "Fantasy", 1, new Account(),1);
        when(bookRepo.save(bookToAdd)).thenReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);

        assertEquals(bookToAdd, addedBook);
        verify(bookRepo).save(bookToAdd);
    }

    @Test
    void deleteBook() {
        int bookId = 1;
        bookService.deleteBook(bookId);
        verify(bookRepo).deleteById(bookId);
    }

}
