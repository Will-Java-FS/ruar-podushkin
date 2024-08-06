package com.revature.SpringProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.SpringProject.controllers.AccountController;
import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.models.Book;
import com.revature.SpringProject.services.AccountService;
import com.revature.SpringProject.services.BookService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTEST
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAccountById_SuccessfulTEST() throws Exception
    {
        // Making sure that there is a model with id 1 existing in a "mock/local repository"
        List<Account> accountList = new ArrayList<>();
        Account account = new Account(1, "username", "password", "email", "user", null);

        accountList.add(account);

        // Set up Mockito to return the mock data when findBookById is called with ID 1
        Mockito.when(accountService.findAccountById(1)).thenReturn(accountList);

        // Convert book to JSON
        String accountJson = objectMapper.writeValueAsString(accountList);

        // Perform GET request
        mvc.perform(MockMvcRequestBuilders.get("/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(accountJson));
    }

    @Test
    public void getAccountById_FailureTEST() throws Exception
    {
        // Making sure that there is a model with id 1 existing in a "mock/local repository"
        Account account = new Account(1, "username", "password", "email", "user", null);

        // Set up Mockito to return null when findBookById is called with ID 2 since none exists with that id
        Mockito.when(accountService.findAccountById(2)).thenReturn(null);

        // Perform GET request
        mvc.perform(MockMvcRequestBuilders.get("/accounts/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void logIn_SuccessfulTEST() throws Exception
    {
        // Mock data for the account
        Account account = new Account(1, "username", "password", "email", "user", null);

        // Set up Mockito to return the mock data when findByUsernameAndByPassword is called with the correct parameters
        Mockito.when(accountService.findByUsernameAndByPassword("username", "password")).thenReturn(account);

        // Convert account to JSON
        String accountJson = objectMapper.writeValueAsString(account);

        // Perform GET request with query parameters
        mvc.perform(MockMvcRequestBuilders.get("/accounts/login")
                        .param("username", "username")
                        .param("password", "password")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(accountJson));
    }

    @Test
    public void logIn_FailureTEST() throws Exception
    {
        // Making sure that there is a model with a username of username and a password of password existing in a "mock/local repository"
        Account account = new Account(1, "username", "password", "email", "user", null);

        // Set up Mockito to return null when findByUsernameAndPassword is called with wrong log in credentials since none exist with those credential
        Mockito.when(accountService.findByUsernameAndByPassword("username", "p4ssw0rd")).thenReturn(null);

        // Perform GET request with query parameters
        mvc.perform(MockMvcRequestBuilders.get("/accounts/login")
                        .param("username", "username")
                        .param("password", "password")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); // Expecting a 400 Bad Request status
    }

    @Test
    public void addAccount_SuccessfulTEST() throws Exception
    {
        // Create mock data
        Account accountToAdd = new Account(1, "username", "password", "email", "user", null);
        Account accountAdded = new Account(1, "username", "password", "email", "user", null);

        // Set up Mockito to return the mock data when addBook is called
        Mockito.when(accountService.createAccount(Mockito.any(Account.class))).thenReturn(accountAdded);

        // Convert bookToAdd to JSON
        String accountToAddJson = objectMapper.writeValueAsString(accountToAdd);

        // Perform POST request
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountToAddJson))
                .andExpect(status().isOk())
                .andExpect(content().json(accountToAddJson));
    }

    @Test
    public void addAccount_FailureTEST() throws Exception
    {
        // Checking to see if the null check is handled properly
        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")) // Null body
                .andExpect(status().isBadRequest());
    }
}