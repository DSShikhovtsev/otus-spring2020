package homework2.service;

import homework2.domain.Book;

import java.util.List;

public interface BookService {

    void save(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBookById(Long id);
    void addAuthorToBook(Long bookId, Long authorId);
    void deleteAuthorFromBook(Long bookId, Long authorId);
    void addGenreToBook(Long bookId, Long genreId);
    void deleteGenreFromBook(Long bookId, Long genreId);
}
