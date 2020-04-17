package homework2.dao;

import homework2.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);
    void update(Author author);
    Author getById(Long id);
    List<Author> findAll();
    void deleteById(Long id);
    void addBookByAuthorId(Long authorId, Long bookId);
    void deleteBookByAuthorId(Long authorId, Long bookId);
}
