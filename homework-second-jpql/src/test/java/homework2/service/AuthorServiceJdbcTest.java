package homework2.service;

import homework2.dao.AuthorDaoJdbc;
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

@DisplayName("Service для работы с авторами")
@DataJpaTest
@Import({AuthorServiceJdbc.class, AuthorDaoJdbc.class})
class AuthorServiceJdbcTest {

    @Autowired
    private AuthorServiceJdbc service;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getById() {
        assertThat(em.find(Author.class, 1L)).isEqualToComparingFieldByField(service.getAuthorById(1L));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void findAll() {
        Author author = em.find(Author.class, 1L);
        Author author1 = em.find(Author.class, 2L);
        List<Author> authors = service.getAllAuthors();
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void insert() {
        service.save(new Author("testInsert"));
        Author author = em.find(Author.class, 3L);
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById(3L));
    }

    @Test
    @DisplayName("обновлять имя автора")
    void update() {
        Author author = em.find(Author.class, 2L);
        author.setName("update");
        service.updateAuthor(author);
        em.refresh(author);
        assertEquals(author.getName(), service.getAuthorById(2L).getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteById() {
        Author author = em.find(Author.class, 1L);
        assertThat(author).isNotNull();
        service.deleteAuthorById(1L);
        em.clear();
        author = em.find(Author.class, 1L);
        assertThat(author).isNull();
    }
}