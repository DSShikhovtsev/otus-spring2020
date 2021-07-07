package hystrix.service.book;

import hystrix.domain.Book;

import java.util.List;

public interface BookService {

    void save(Book book);
    Book getBookById(String id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBookById(String id);
    void addAuthorToBook(Book book, String id);
    void addGenreToBook(Book book, String id);
    void deleteAuthorFomBook(Book book, String id);
    void deleteGenreFromBook(Book book, String id);
}
