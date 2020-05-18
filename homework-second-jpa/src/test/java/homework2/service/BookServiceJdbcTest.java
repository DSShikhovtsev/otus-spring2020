package homework2.service;

import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Comment;
import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service для работы с книгами")
@SpringBootTest
class BookServiceJdbcTest {

    @MockBean
    private BookServiceJdbc service;

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    void getById() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        assertThat(book).isEqualToComparingFieldByField(service.getBookById(1L));
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
        assertEquals(books.toString(), service.getAllBooks().toString());
    }

    @Test
    @DisplayName("добавлять книгу в таблицу")
    void insert() {
        service.save(new Book("test"));
        Book book = service.getBookById(3L);
        assertThat(book).isEqualToComparingFieldByField(service.getBookById(3L));
    }

    @Test
    @DisplayName("обновлять заголовок книги")
    void update() {
        Book book = service.getBookById(2L);
        book.setTitle("test");
        service.updateBook(book);
        assertEquals(book.getTitle(), service.getBookById(2L).getTitle());
    }

    @Test
    @DisplayName("удалять книгу из таблицы")
    void deleteById() {
        Book book = service.getBookById(1L);
        assertThat(book).isNotNull();
        service.deleteBookById(1L);
        book = service.getBookById(1L);
        assertThat(book).isNull();
    }
}