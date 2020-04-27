package homework2.service;

import homework2.domain.Author;

import java.util.List;

public interface AuthorService {

    void putNewAuthor(Author author);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthorById(Long id);
    void addBookToAuthor(Long authorId, Long bookId);
    void deleteBookFromAuthor(Long authorId, Long bookId);
}
