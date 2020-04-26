DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors_books;
DROP TABLE IF EXISTS books_genres;

CREATE TABLE authors(id BIGSERIAL PRIMARY KEY, name VARCHAR(255));
CREATE TABLE books(id BIGSERIAL PRIMARY KEY, title VARCHAR(255));
CREATE TABLE genres(id BIGSERIAL PRIMARY KEY, description VARCHAR(255));
CREATE TABLE authors_books(id_author BIGINT NOT NULL, id_book BIGINT NOT NULL,
    FOREIGN KEY(id_author) REFERENCES authors(id),
    FOREIGN KEY(id_book) REFERENCES books(id)
);
CREATE TABLE books_genres(id_book BIGINT NOT NULL, id_genre BIGINT NOT NULL,
    FOREIGN KEY(id_book) REFERENCES books(id),
    FOREIGN KEY(id_genre) REFERENCES genres(id)
);

ALTER TABLE authors_books ADD PRIMARY KEY(id_author, id_book);
ALTER TABLE books_genres ADD PRIMARY KEY(id_book, id_genre);