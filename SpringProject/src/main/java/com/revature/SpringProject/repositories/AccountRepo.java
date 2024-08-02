package com.revature.SpringProject.repositories;

import com.revature.SpringProject.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>
{
    public Optional<Account> findByUsernameAndPassword(String username, String password);
}
