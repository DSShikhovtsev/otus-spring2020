package homework2.service;

import homework2.domain.Book;

import java.util.List;

public interface BookService {

    void save(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBookById(Long id);
    void addAuthorByBookId(Long bookId, Long authorId);
    void deleteAuthorByBookId(Long bookId, Long authorId);
    void addGenreByBookId(Long bookId, Long genreId);
    void deleteGenreByBookId(Long bookId, Long genreId);
}
