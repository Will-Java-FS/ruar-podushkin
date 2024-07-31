SET search_path TO libraryschema;

DROP TABLE IF EXISTS Books;

CREATE TABLE IF NOT EXISTS Books (
	book_id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	publication_year INTEGER,
	genre VARCHAR(100),
	available_copies INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Accounts (
	account_id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL
);


INSERT INTO Books (title, author, publication_year, genre, available_copies)
VALUES 
    ('To Kill a Mockingbird', 'Harper Lee', 1960, 'Fiction', 5),
    ('1984', 'George Orwell', 1949, 'Science Fiction', 3),
    ('Pride and Prejudice', 'Jane Austen', 1813, 'Romance', 4),
    ('The Catcher in the Rye', 'J.D. Salinger', 1951, 'Fiction', 2),
    ('The Great Gatsby', 'F. Scott Fitzgerald', 1925, 'Fiction', 6);


INSERT INTO Accounts (username, password, email)
VALUES 
    ('johndoe', 'hashed_password_1', 'john.doe@email.com'),
    ('janesmit', 'hashed_password_2', 'jane.smith@email.com'),
    ('bobwilson', 'hashed_password_3', 'bob.wilson@email.com'),
    ('alicejones', 'hashed_password_4', 'alice.jones@email.com'),
    ('charliebrwn', 'hashed_password_5', 'charlie.brown@email.com');

SELECT * FROM Books;


