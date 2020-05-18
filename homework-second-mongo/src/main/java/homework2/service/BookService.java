package homework2.service;

import homework2.domain.Book;

import java.util.List;

public interface BookService {

    void save(Book book);
    Book getBookById(String id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBookById(String id);
}
