package docker.service;

import docker.domain.Author;
import docker.repository.AuthorRepository;
import docker.service.author.AuthorService;
import docker.service.author.AuthorServiceMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с авторами")
@SpringBootTest(classes = AuthorServiceMongo.class)
class AuthorServiceMongoTest {

    @Autowired
    private AuthorService service;

    @MockBean
    private AuthorRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        List<Author> list = new ArrayList<>();
        list.add(new Author("1", "test"));
        list.add(new Author("2", "test1"));
        Mockito.when(repository.findById("1")).thenReturn(Optional.of(new Author("1", "test"))).thenReturn(Optional.empty());
        Mockito.when(repository.findById("2")).thenReturn(Optional.of(new Author("2", "test1"))).thenReturn(Optional.of(new Author("2", "update")));
        given(repository.findById("3")).willReturn(Optional.of(new Author("3", "testInsert")));
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getAuthorById() {
        Author author = new Author("1", "test");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById("1"));
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void addAuthor() {
        Author author = new Author("3", "testInsert");
        service.save(author);
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById("3"));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void getAllAuthors() {
        Author author = new Author("1", "test");
        Author author1 = new Author("2", "test1");
        List<Author> authors = service.getAllAuthors();
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
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
        Author author = service.getAuthorById("1");
        assertThat(author).isNotNull();
        service.deleteAuthorById("1");
        author = service.getAuthorById("1");
        assertThat(author).isNull();
    }
}