package com.revature.SpringProject;

import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.repositories.AccountRepo;
import com.revature.SpringProject.services.AccountService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AccountServiceTest {
    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    //TODO: Add tests for user and admin roles and findById()
    /*
    @Test
    void findAccountByExistingId() {
        // This tests if a user account can be retrieved from the repo

        Account expectedAccount = new Account();
        expectedAccount.setId(1);
        when(accountRepo.findById(1)).thenReturn(Optional.of(expectedAccount));

        List<Account> result = accountService.findAccountById(1);

        assertNotNull(result);
        assertEquals(1, result.get(0).getId());
        verify(accountRepo).findById(1);
    }

    @Test
    void findAccountByNonexistingId() {
        Account expectedAccount = new Account();
        when(accountRepo.findById(33)).thenReturn(Optional.empty());

        //Account result = accountService.findAccountById(33);

        //assertNull(result);
        verify(accountRepo).findById(33);
    }*/

    @Test
    void createValidAccount() {
        Account accountToCreate = new Account();
        accountToCreate.setUsername("testUser1");
        accountToCreate.setPassword("testPass1");
        accountToCreate.setEmail("testUser@gmail.com");
        when(accountRepo.save(accountToCreate)).thenReturn(accountToCreate);

        Account result = accountService.createAccount(accountToCreate);

        assertNotNull(result);
        assertEquals("testUser1", result.getUsername());
        verify(accountRepo).save(accountToCreate);
    }

    @Test
    void findByValidUsernameAndPassword() {
        Account expectedAccount = new Account();
        expectedAccount.setUsername("testUser");
        expectedAccount.setPassword("testPass");
        when(accountRepo.findByUsernameAndPassword("testUser", "testPass"))
                .thenReturn(Optional.of(expectedAccount));

        Account result = accountService.findByUsernameAndByPassword("testUser", "testPass");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("testPass", result.getPassword());
        verify(accountRepo).findByUsernameAndPassword("testUser", "testPass");
    }

    @Test
    void findByInvalidUsernameAndPassword() {
        when(accountRepo.findByUsernameAndPassword("wrongUser", "wrongPass"))
                .thenReturn(Optional.empty());

        Account result = accountService.findByUsernameAndByPassword("wrongUser", "wrongPass");

        assertNull(result);
        verify(accountRepo).findByUsernameAndPassword("wrongUser", "wrongPass");
    }


}
