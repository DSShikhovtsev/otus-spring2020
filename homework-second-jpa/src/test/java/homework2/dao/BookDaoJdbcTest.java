package homework2.dao;

import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Dao для работы с книгами")
@RunWith(SpringRunner.class)
@DataJpaTest
class BookDaoJdbcTest {

    @Autowired
    private BookDao dao;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getById() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        assertThat(book).isEqualToComparingFieldByField(dao.findById(1L).get());
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void findAll() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        books.add(book);
        Book book1 = new Book(2L, "book1");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(1L, "genre"));
        books.add(book1);
        assertEquals(books.toString(), dao.findAll().toString());
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void insert() {
        dao.save(new Book("test"));
        Book book = dao.findById(3L).orElse(null);
        assertThat(book).isEqualToComparingFieldByField(dao.findById(3L).get());
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void update() {
        Book book = dao.findById(2L).get();
        book.setTitle("test");
        dao.save(book);
        assertEquals(book.getTitle(), dao.findById(2L).get().getTitle());
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteById() {
        Book book = dao.findById(1L).get();
        assertThat(book).isNotNull();
        dao.deleteById(1L);
        book = dao.findById(1L).orElse(null);
        assertThat(book).isNull();
    }
}