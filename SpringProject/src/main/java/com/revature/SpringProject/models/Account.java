package com.revature.SpringProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private int id;

    @Column(nullable = false) // Could set up more verification for username and passwords if we wanted to here
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Book> books;
}
