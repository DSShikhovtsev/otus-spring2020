package homework2.dao;

import homework2.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Dao для работы с авторами")
@DataJpaTest
@Import({AuthorDaoJdbc.class})
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getById() {
        assertThat(em.find(Author.class, 1L)).isEqualToComparingFieldByField(dao.getById(1L));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void findAll() {
        Author author = em.find(Author.class, 1L);
        Author author1 = em.find(Author.class, 2L);
        List<Author> authors = dao.findAll();
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void insert() {
        dao.save(new Author("testInsert"));
        Author author = em.find(Author.class, 3L);
        assertThat(author).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("обновлять имя автора")
    void update() {
        Author author = em.find(Author.class, 2L);
        author.setName("update");
        dao.update(author);
        em.refresh(author);
        assertEquals(author.getName(), dao.getById(2L).getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteById() {
        Author author = em.find(Author.class, 1L);
        assertThat(author).isNotNull();
        dao.deleteById(1L);
        em.clear();
        author = em.find(Author.class, 1L);
        assertThat(author).isNull();
    }
}