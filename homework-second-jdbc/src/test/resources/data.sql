insert into authors(name) values
    ('test'),
    ('test1');

insert into books(title) values
    ('book'),
    ('book1');

insert into genres(description) values
    ('genre'),
    ('genre1');

insert into authors_books(id_author, id_book) values
    (1, 1),
    (2, 2);

insert into books_genres(id_book, id_genre) values
    (1, 2),
    (2, 1);