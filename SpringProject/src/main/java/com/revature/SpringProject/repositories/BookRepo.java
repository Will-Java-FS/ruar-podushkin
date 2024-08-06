package com.revature.SpringProject.repositories;

import com.revature.SpringProject.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer>
{
    public List<Book> findAll();
}
