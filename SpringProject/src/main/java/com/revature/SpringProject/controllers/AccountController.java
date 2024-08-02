package com.revature.SpringProject.controllers;

import com.revature.SpringProject.models.Account;
import com.revature.SpringProject.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public ResponseEntity<Account> logIn(@RequestParam String username, @RequestParam String password)
    {
        Account account = as.findByUsernameAndByPassword(username, password);
        if(account != null)
            return new ResponseEntity<>(account, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
    {
        if(account != null)
            return new ResponseEntity<>(as.createAccount(account), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
