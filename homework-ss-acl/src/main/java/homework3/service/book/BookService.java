package homework3.service.book;

import homework3.domain.Book;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface BookService {

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void save(Book book);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Book getBookById(Long id);

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Book> getAllBooks();

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void updateBook(Book book);

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void deleteBook(Book book);

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void addAuthorToBook(Book book, Long authorId);

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void deleteAuthorFromBook(Book book, Long authorId);

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void addGenreToBook(Book book, Long genreId);

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void deleteGenreFromBook(Book book, Long genreId);
}
