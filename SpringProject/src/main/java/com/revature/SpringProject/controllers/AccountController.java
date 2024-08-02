package com.revature.SpringProject.controllers;

import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController
{
    @Autowired
    AccountService as;

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable("id") int id)
    {
        Account account = as.findAccountById(id);

        if(account != null)
            return new ResponseEntity<>(account, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
