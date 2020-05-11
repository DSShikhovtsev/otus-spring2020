DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors_books;
DROP TABLE IF EXISTS books_genres;
DROP TABLE IF EXISTS books_comments;

CREATE TABLE authors(id BIGSERIAL PRIMARY KEY, name VARCHAR(255));
CREATE TABLE books(id BIGSERIAL PRIMARY KEY, title VARCHAR(255));
CREATE TABLE genres(id BIGSERIAL PRIMARY KEY, description VARCHAR(255));
CREATE TABLE comments(id BIGSERIAL PRIMARY KEY, comment VARCHAR(255));
CREATE TABLE authors_books(id_author BIGINT NOT NULL, id_book BIGINT NOT NULL,
    FOREIGN KEY(id_author) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY(id_book) REFERENCES books(id) ON DELETE CASCADE
);
CREATE TABLE books_genres(id_book BIGINT NOT NULL, id_genre BIGINT NOT NULL,
    FOREIGN KEY(id_book) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY(id_genre) REFERENCES genres(id) ON DELETE CASCADE
);
CREATE TABLE books_comments(id_book BIGINT NOT NULL, id_comment BIGINT NOT NULL,
    FOREIGN KEY(id_book) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY(id_comment) REFERENCES comments(id) ON DELETE CASCADE
);

ALTER TABLE authors_books ADD PRIMARY KEY(id_author, id_book);
ALTER TABLE books_genres ADD PRIMARY KEY(id_book, id_genre);
ALTER TABLE books_comments ADD PRIMARY KEY(id_book, id_comment);