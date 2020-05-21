package homework2.service;

import homework2.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service для работы с авторами")
@SpringBootTest
class AuthorServiceJdbcTest {

    @Autowired
    private AuthorServiceJdbc service;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getById() {
        Author author = new Author(1L, "test");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById(1L));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void findAll() {
        Author author = new Author(1L, "test");
        Author author1 = new Author(2L, "test1");
        Author author2 = new Author(3L, "testInsert");
        List<Author> authors = service.getAllAuthors();
        assertThat(authors).hasSize(3).containsExactlyInAnyOrder(author, author1, author2);
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void insert() {
        service.save(new Author("testInsert"));
        Author author = service.getAuthorById(3L);
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById(3L));
    }

    @Test
    @DisplayName("обновлять имя автора")
    void update() {
        Author author = service.getAuthorById(2L);
        author.setName("update");
        service.updateAuthor(author);
        assertEquals(author.getName(), service.getAuthorById(2L).getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteById() {
        Author author = service.getAuthorById(1L);
        assertThat(author).isNotNull();
        service.deleteAuthorById(1L);
        author = service.getAuthorById(1L);
        assertThat(author).isNull();
    }
}