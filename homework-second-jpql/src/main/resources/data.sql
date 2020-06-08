insert into authors(name) values
    ('Petya'),
    ('Kolya'),
    ('Oleg'),
    ('Dasha');

insert into books(title) values
    ('About sea'),
    ('About Stalin'),
    ('About pigs'),
    ('About Witcher'),
    ('About ghosts'),
    ('About magic'),
    ('About cook');

insert into genres(description) values
    ('History'),
    ('Nature'),
    ('Mystic'),
    ('Legend'),
    ('Animals');

insert into comments(comment) values
    ('Comment 1'),
    ('Comment 2'),
    ('Comment 3'),
    ('Comment 4'),
    ('Comment 5'),
    ('Comment 6'),
    ('Comment 7');

insert into authors_books(id_author, id_book) values
    ((SELECT id FROM authors WHERE name = 'Petya'), (SELECT id FROM books WHERE title = 'About Stalin')),
    ((SELECT id FROM authors WHERE name = 'Petya'), (SELECT id FROM books WHERE title = 'About magic')),
    ((SELECT id FROM authors WHERE name = 'Kolya'), (SELECT id FROM books WHERE title = 'About sea')),
    ((SELECT id FROM authors WHERE name = 'Kolya'), (SELECT id FROM books WHERE title = 'About pigs')),
    ((SELECT id FROM authors WHERE name = 'Kolya'), (SELECT id FROM books WHERE title = 'About cook')),
    ((SELECT id FROM authors WHERE name = 'Oleg'), (SELECT id FROM books WHERE title = 'About ghosts')),
    ((SELECT id FROM authors WHERE name = 'Oleg'), (SELECT id FROM books WHERE title = 'About magic')),
    ((SELECT id FROM authors WHERE name = 'Oleg'), (SELECT id FROM books WHERE title = 'About cook')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About sea')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About Stalin')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About pigs')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About Witcher')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About ghosts')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About magic')),
    ((SELECT id FROM authors WHERE name = 'Dasha'), (SELECT id FROM books WHERE title = 'About cook'));

insert into books_genres(id_book, id_genre) values
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM genres WHERE description = 'History')),
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM genres WHERE description = 'Nature')),
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM genres WHERE description = 'Mystic')),
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM genres WHERE description = 'Legend')),
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM genres WHERE description = 'Animals')),
    ((SELECT id FROM books WHERE title = 'About Stalin'), (SELECT id FROM genres WHERE description = 'History')),
    ((SELECT id FROM books WHERE title = 'About pigs'), (SELECT id FROM genres WHERE description = 'Nature')),
    ((SELECT id FROM books WHERE title = 'About pigs'), (SELECT id FROM genres WHERE description = 'Animals')),
    ((SELECT id FROM books WHERE title = 'About Witcher'), (SELECT id FROM genres WHERE description = 'History')),
    ((SELECT id FROM books WHERE title = 'About Witcher'), (SELECT id FROM genres WHERE description = 'Mystic')),
    ((SELECT id FROM books WHERE title = 'About Witcher'), (SELECT id FROM genres WHERE description = 'Legend')),
    ((SELECT id FROM books WHERE title = 'About ghosts'), (SELECT id FROM genres WHERE description = 'Mystic')),
    ((SELECT id FROM books WHERE title = 'About ghosts'), (SELECT id FROM genres WHERE description = 'Legend')),
    ((SELECT id FROM books WHERE title = 'About magic'), (SELECT id FROM genres WHERE description = 'History')),
    ((SELECT id FROM books WHERE title = 'About magic'), (SELECT id FROM genres WHERE description = 'Mystic')),
    ((SELECT id FROM books WHERE title = 'About cook'), (SELECT id FROM genres WHERE description = 'Nature')),
    ((SELECT id FROM books WHERE title = 'About cook'), (SELECT id FROM genres WHERE description = 'Animals'));

insert into books_comments(id_book, id_comment) values
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM comments WHERE comment = 'Comment 1')),
    ((SELECT id FROM books WHERE title = 'About cook'), (SELECT id FROM comments WHERE comment = 'Comment 2')),
    ((SELECT id FROM books WHERE title = 'About magic'), (SELECT id FROM comments WHERE comment = 'Comment 3')),
    ((SELECT id FROM books WHERE title = 'About Witcher'), (SELECT id FROM comments WHERE comment = 'Comment 4')),
    ((SELECT id FROM books WHERE title = 'About Witcher'), (SELECT id FROM comments WHERE comment = 'Comment 5')),
    ((SELECT id FROM books WHERE title = 'About Witcher'), (SELECT id FROM comments WHERE comment = 'Comment 6')),
    ((SELECT id FROM books WHERE title = 'About sea'), (SELECT id FROM comments WHERE comment = 'Comment 7'));