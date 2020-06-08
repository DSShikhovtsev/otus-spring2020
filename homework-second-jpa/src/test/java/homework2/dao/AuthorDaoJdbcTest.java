package homework2.dao;

import homework2.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Dao для работы с авторами")
@DataJpaTest
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao dao;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getById() {
        Author author = new Author(1L, "test");
        assertThat(author).isEqualToComparingFieldByField(dao.findById(1L).get());
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void findAll() {
        Author author = new Author(1L, "test");
        Author author1 = new Author(2L, "test1");
        List<Author> authors = dao.findAll();
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void insert() {
        dao.save(new Author("testInsert"));
        Author author = dao.findById(3L).orElse(null);
        assertThat(author).isEqualToComparingFieldByField(dao.findById(3L).get());
    }

    @Test
    @DisplayName("обновлять имя автора")
    void update() {
        Author author = dao.findById(2L).orElse(new Author());
        author.setName("update");
        dao.save(author);
        assertEquals(author.getName(), dao.findById(2L).get().getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteById() {
        Author author = dao.findById(1L).get();
        assertThat(author).isNotNull();
        dao.deleteById(1L);
        author = dao.findById(1L).orElse(null);
        assertThat(author).isNull();
    }
}