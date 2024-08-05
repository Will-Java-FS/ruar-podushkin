package com.revature.SpringProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.SpringProject.controllers.BookController;
import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.repositories.BookRepo;
import com.revature.SpringProject.services.BookService;
import org.assertj.core.util.Arrays;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTEST
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllBooks_SuccessfulTEST() throws Exception
    {
        // Create mock data
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(Book.builder().id(1).title("Book One").author("Author One").year(2020).genre("Fiction").availableCopies(5).build());
        listOfBooks.add(Book.builder().id(2).title("Book Two").author("Author Two").year(2021).genre("Non-Fiction").availableCopies(3).build());

        // Set up Mockito to return the mock data
        Mockito.when(bookService.findAllBooks()).thenReturn(listOfBooks);

        // Perform GET request
        mvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfBooks)));
    }

    @Test
    public void addBook_SuccessfulTEST() throws Exception
    {
        // Create mock data
        Book bookToAdd = new Book(1, "Book One", "Author One", 2020, "Fiction", 5, null, null);
        Book addedBook = new Book(1, "Book One", "Author One", 2020, "Fiction", 5, null, null);

        // Set up Mockito to return the mock data when addBook is called
        Mockito.when(bookService.addBook(Mockito.any(Book.class))).thenReturn(addedBook);

        // Convert bookToAdd to JSON
        String bookToAddJson = objectMapper.writeValueAsString(bookToAdd);

        // Perform POST request
        mvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookToAddJson))
                .andExpect(status().isOk())
                .andExpect(content().json(bookToAddJson));
    }

    @Test
    public void addBook_FailureTEST() throws Exception
    {
        // Checking to see if the null check is handled properly
        mvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")) // Null body
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBookById_SuccessTEST() throws Exception
    {
        // Making sure that there is a model with id 1 existing in a "mock/local repository"
        Book book = new Book(1, "Book One", "Author One", 2020, "Fiction", 5, null, null);

        // Set up Mockito to return the mock data when findBookById is called with ID 1
        Mockito.when(bookService.findBookById(1)).thenReturn(book);

        // Convert book to JSON
        String bookJson = objectMapper.writeValueAsString(book);

        // Perform GET request
        mvc.perform(MockMvcRequestBuilders.get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(bookJson));
    }

    @Test
    public void getBooksById_IdNOT_FOUND_TEST() throws Exception
    {
        // Making sure that there is a model with id 1 existing in a "mock/local repository"
        Book book = new Book(1, "Book One", "Author One", 2020, "Fiction", 5, null, null);

        // Set up Mockito to return the mock data when findBookById is called with ID 1
        Mockito.when(bookService.findBookById(2)).thenReturn(null);

        // Perform GET request
        mvc.perform(MockMvcRequestBuilders.get("/books/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // There is no book with id 2 in local repository therefore: NOT_FOUND
    }

    // This endpoint in our current setup always returns a 200 so that it can be fired upon many times
    @Test
    public void deleteBook_SuccessfulTEST() throws Exception
    {
        // Perform DELETE request
        mvc.perform(MockMvcRequestBuilders.delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Expecting a 200 OK status
    }

    @Test
    public void updateBook_SuccessfulTEST() throws Exception
    {
        // Create mock data
        Book bookToUpdate = new Book(1, "Updated Book", "Updated Author II", 2023, "Science Fiction", 10, null, null);
        Book updatedBook = new Book(1, "Updated Book", "Updated Author II", 2023, "Science Fiction", 10, null, null);

        // Set up Mockito to return the updated book
        Mockito.when(bookService.updateBook(Mockito.any(Book.class))).thenReturn(updatedBook);

        // Convert bookToUpdate to JSON
        String bookToUpdateJson = objectMapper.writeValueAsString(bookToUpdate);

        // Perform PUT request
        mvc.perform(MockMvcRequestBuilders.put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookToUpdateJson))
                .andExpect(status().isOk())
                .andExpect(content().json(bookToUpdateJson));
    }

    @Test
    public void updateBook_FailureTEST() throws Exception
    {
        // Create an invalid book with missing required fields
        Book invalidBook = new Book(1, null, null, 2023, "Science Fiction", 10, null, null);

        // Convert invalidBook to JSON
        String invalidBookJson = objectMapper.writeValueAsString(invalidBook);

        // Perform PUT request with invalid data
        mvc.perform(MockMvcRequestBuilders.put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidBookJson))
                .andExpect(status().isBadRequest()); // Expecting a 400 Bad Request status
    }
}
