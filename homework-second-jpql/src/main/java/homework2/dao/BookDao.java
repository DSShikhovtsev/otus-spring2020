package homework2.dao;

import homework2.domain.Book;

import java.util.List;

public interface BookDao {

    void save(Book book);
    void update(Book book);
    Book getById(Long id);
    List<Book> findAll();
    void deleteById(Long id);
    void addGenreByBookId(Long bookId, Long genreId);
    void deleteGenreByBookId(Long bookId, Long genreId);
}
