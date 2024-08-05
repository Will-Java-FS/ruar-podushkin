package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Account findAccountById(int id)
    {
        return ar.findById(id).orElse(null);
    }

    // User story 6: As a user, I can create an account to hold my items
    @Override
    public Account createAccount(Account account)
    {
        return ar.save(account);
    }

    // User story 7: As a user, I can log into my account
    @Override
    public Account findByUsernameAndByPassword(String username, String password)
    {
        return ar.findByUsernameAndPassword(username, password).orElse(null);
    }
}
