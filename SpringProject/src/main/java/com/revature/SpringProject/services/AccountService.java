package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService
{
    @Autowired
    AccountRepo ar;

    @Override
    public Account findAccountById(int id)
    {
        return ar.findById(id).orElse(null);
    }

    @Override
    public Account createAccount(Account account)
    {
        return ar.save(account);
    }

    @Override
    public Account findByUsernameAndByPassword(String username, String password)
    {
        return ar.findByUsernameAndPassword(username, password).orElse(null);
    }
}
