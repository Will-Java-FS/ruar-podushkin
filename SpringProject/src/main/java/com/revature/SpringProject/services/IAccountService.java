package com.revature.SpringProject.services;

import com.revature.SpringProject.models.Account;

import java.util.List;

public interface IAccountService
{
    public List<Account> findAccountById(int id);
    public Account createAccount(Account account);
    public Account findByUsernameAndByPassword(String username, String password);
}
