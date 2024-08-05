package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService
{
    AccountRepo ar;

    @Autowired
    public AccountService(AccountRepo ar)
    {
        this.ar = ar;
    }

    // User story 8: As a user, I can view the Items associated with my account
    @Override
    public List<Account> findAccountById(int id)
    {
        Account account = ar.findById(id).orElse(null); // getting the account

        // If the account under that id was an admin, return all entities in the database (all users)
        if(account.getRole().equals("admin"))
            return ar.findAll();

        List<Account> accountAsList = new ArrayList<>();
        accountAsList.add(account);
        return accountAsList;
    }

    // User story 6: As a user, I can create an account to hold my items
    @Override
    public Account createAccount(Account account)
    {
        account.setRole("user"); // Scrubbing whatever the user submitted as their role so that it is a user
        return ar.save(account);
    }

    // User story 7: As a user, I can log into my account
    @Override
    public Account findByUsernameAndByPassword(String username, String password)
    {
        return ar.findByUsernameAndPassword(username, password).orElse(null);
    }
}
