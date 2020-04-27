package homework2.dao;

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

@DisplayName("Dao для работы с книгами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({BookDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getById() {
        Book book = new Book(1L, "book");
        book.getGenres().add(new Genre(2L, "genre1"));
        assertEquals(book.toString(), dao.getById(1L).toString());
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void findAll() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(1L, "book");
        book.getGenres().add(new Genre(2L, "genre1"));
        books.add(book);
        Book book1 = new Book(2L, "book1");
        book1.getGenres().add(new Genre(1L, "genre"));
        books.add(book1);
        assertEquals(books.toString(), dao.findAll().toString());
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void insert() {
        dao.insert(new Book("test"));
        Book book = new Book(3L, "test");
        assertThat(book).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void update() {
        Book book = new Book(2L, "update");
        book.getGenres().add(new Genre(1L, "genre"));
        dao.update(book);
        assertEquals(book.toString(), dao.getById(2L).toString());
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteById() {
        dao.deleteById(1L);
        assertThrows(Exception.class, () -> dao.getById(1L));
    }
}