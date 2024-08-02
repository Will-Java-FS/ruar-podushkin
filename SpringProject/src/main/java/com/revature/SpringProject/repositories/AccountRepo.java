package com.revature.SpringProject.repositories;

import com.revature.SpringProject.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>
{

}
