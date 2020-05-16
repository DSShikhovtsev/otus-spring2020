package homework2.dao;

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

@DisplayName("Dao для работы с авторами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({AuthorDaoJdbc.class})
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc dao;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getById() {
        Author author = new Author(1L, "test");
        assertEquals(author.toString(), dao.getById(1L).toString());
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void findAll() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author(1L, "test");
        authors.add(author);
        Author author1 = new Author(2L, "test1");
        authors.add(author1);
        assertEquals(authors.toString(), dao.findAll().toString());
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void insert() {
        dao.insert(new Author("testInsert"));
        Author author = new Author(3L, "testInsert");
        assertThat(author).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("обновлять имя автора")
    void update() {
        Author author = new Author(2L, "update");
        dao.update(author);
        assertEquals(author.toString(), dao.getById(2L).toString());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteById() {
        dao.deleteById(1L);
        assertThrows(Exception.class, () -> dao.getById(1L));
    }
}