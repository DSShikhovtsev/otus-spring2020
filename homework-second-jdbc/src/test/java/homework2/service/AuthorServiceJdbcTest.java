package homework2.service;

import homework2.dao.AuthorDaoJdbc;
import homework2.domain.Author;
import homework2.domain.Book;
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

@DisplayName("Service для работы с авторами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({AuthorServiceJdbc.class, AuthorDaoJdbc.class})
class AuthorServiceJdbcTest {

    @Autowired
    private AuthorServiceJdbc service;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getAuthorById() {
        Author author = new Author(1L, "test");
        author.getBooks().add(new Book(1L, "book"));
        assertEquals(author.toString(), service.getAuthorById(1L).toString());
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author(1L, "test");
        author.getBooks().add(new Book(1L, "book"));
        authors.add(author);
        Author author1 = new Author(2L, "test1");
        author1.getBooks().add(new Book(2L, "book1"));
        authors.add(author1);
        assertEquals(authors.toString(), service.getAllAuthors().toString());
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void putNewAuthor() {
        service.putNewAuthor(new Author("testInsert"));
        Author author = new Author(3L, "testInsert");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById(3L));
    }

    @Test
    @DisplayName("обновлять имя автора")
    void updateAuthor() {
        Author author = new Author(2L, "update");
        author.getBooks().add(new Book(2L, "book1"));
        service.updateAuthor(author);
        assertEquals(author.toString(), service.getAuthorById(2L).toString());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteAuthorById() {
        service.deleteAuthorById(1L);
        assertThrows(Exception.class, () -> service.getAuthorById(1L));
    }

    @Test
    @DisplayName("добавлять книгу к автору")
    void addBookByAuthorId() {
        Book book = new Book(1L, "book");
        service.addBookByAuthorId(2L, 1L);
        assertThat(book).isEqualToComparingFieldByField(service.getAuthorById(2L).getBooks().get(0));
    }

    @Test
    @DisplayName("удалять книгу из автора")
    void deleteBookByAuthorId() {
        Book book = new Book(1L, "book");
        service.deleteBookByAuthorId(1L, 1L);
        assertFalse(service.getAuthorById(1L).getBooks().contains(book));
    }
}