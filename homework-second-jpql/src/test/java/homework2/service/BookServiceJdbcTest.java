package homework2.service;

import homework2.dao.BookDaoJdbc;
import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Service для работы с книгами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({BookServiceJdbc.class, BookDaoJdbc.class})
class BookServiceJdbcTest {

    @Autowired
    private BookServiceJdbc service;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getBookById() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        assertEquals(book.toString(), service.getBookById(1L).toString());
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void getAllBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        books.add(book);
        Book book1 = new Book(2L, "book1");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(1L, "genre"));
        books.add(book1);
        assertEquals(books.toString(), service.getAllBooks().toString());
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void putNewBook() {
        service.putNewBook(new Book("test"));
        Book book = new Book(3L, "test");
        assertThat(book).isEqualToComparingFieldByField(service.getBookById(3L));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void updateBook() {
        Book book = new Book(2L, "update");
        book.getAuthors().add(new Author(2L, "test1"));
        book.getGenres().add(new Genre(1L, "genre"));
        service.updateBook(book);
        assertEquals(book.toString(), service.getBookById(2L).toString());
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteBookById() {
        service.deleteBookById(1L);
        assertThrows(Exception.class, () -> service.getBookById(1L));
    }

    @Test
    @DisplayName("добавлять автора к книге")
    void addAuthorByBookId() {
        Author author = new Author(1L, "test");
        service.addAuthorByBookId(2L, 1L);
        assertThat(author).isEqualToComparingFieldByField(service.getBookById(2L).getAuthors().get(0));
    }

    @Test
    @DisplayName("удалять автора из книги")
    void deleteAuthorByBookId() {
        Author author = new Author(1L, "test");
        service.deleteAuthorByBookId(1L, 1L);
        assertFalse(service.getBookById(1L).getAuthors().contains(author));
    }

    @Test
    @DisplayName("добавлять жанр к книге")
    void addGenreByBookId() {
        Genre genre = new Genre(2L, "genre1");
        service.addGenreByBookId(1L, 2L);
        assertThat(genre).isEqualToComparingFieldByField(service.getBookById(1L).getGenres().get(0));
    }

    @Test
    @DisplayName("удалять жанр из книги")
    void deleteGenreByBookId() {
        Genre genre = new Genre(2L, "genre1");
        service.deleteGenreByBookId(1L, 2L);
        assertFalse(service.getBookById(1L).getGenres().contains(genre));
    }
}