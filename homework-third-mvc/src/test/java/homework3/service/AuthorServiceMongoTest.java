package homework3.service;

import homework3.AbstractServiceTest;
import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service для работы с авторами")
class AuthorServiceMongoTest extends AbstractServiceTest {

    @Autowired
    private AuthorService service;

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getAuthorById() {
        Author author = new Author("1", "author");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById("1"));
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void save() {
        service.save(new Author("3", "testInsert"));
        Author author = service.getAuthorById("3");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById("3"));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void getAllAuthors() {
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "update");
        Author author2 = new Author("3", "testInsert");
        List<Author> authors = service.getAllAuthors();
        assertThat(authors).hasSize(3).containsExactlyInAnyOrder(author, author1, author2);
    }

    @Test
    @DisplayName("обновлять имя автора")
    void updateAuthor() {
        Author author = service.getAuthorById("2");
        author.setName("update");
        service.updateAuthor(author);
        assertEquals(author.getName(), service.getAuthorById("2").getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteAuthorById() {
        Author author = service.getAuthorById("2");
        assertThat(author).isNotNull();
        service.deleteAuthorById("2");
        author = service.getAuthorById("2");
        assertThat(author).isNull();
    }
}