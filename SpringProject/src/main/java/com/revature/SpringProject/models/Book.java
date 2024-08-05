package com.revature.SpringProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", updatable = false)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_year")
    private int year;

    private String genre;

    @Column(name = "available_copies")
    private int availableCopies;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "accounts_fk", nullable = false)
    private Account account;

    /*
    * The below properties were made with external help. Since the above join creates a relationship using JPA with the accounts_fk
    * field in the database, we couldn't just then add another local field accounts_fk to be included in our JSON requests/responses
    *
    * By making it transient and having the custom setter and getter, we were able to add this accounts_fk field to the JSON body of
    * the book object so that we could submit a fk with our requests (and receive one)
    * */
    @Transient
    private Integer accountsFk; // Used for JSON input/output only

    @JsonGetter("accountsFk")
    public Integer getAccountsFk()
    {
        return account != null ? account.getId() : null;
    }

    @JsonSetter("accountsFk")
    public void setAccountsFk(Integer accountsFk)
    {
        this.accountsFk = accountsFk;
        // Set the account field based on accountsFk value
        if (accountsFk != null) {
            this.account = new Account();
            this.account.setId(accountsFk);
        }
    }
}