package homework2.service;

import homework2.domain.Author;

import java.util.List;

public interface AuthorService {

    void save(Author author);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthorById(Long id);
    void addBookByAuthorId(Long authorId, Long bookId);
    void deleteBookByAuthorId(Long authorId, Long bookId);
}
