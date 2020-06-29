package homework3.service;

import homework3.domain.Author;
import homework3.repository.AuthorRepository;
import homework3.service.author.AuthorService;
import homework3.service.author.AuthorServiceJpa;
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
@SpringBootTest(classes = AuthorServiceJpa.class)
class AuthorServiceJpaTest {

    @Autowired
    private AuthorService service;

    @MockBean
    private AuthorRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        List<Author> list = new ArrayList<>();
        list.add(new Author(1L, "test"));
        list.add(new Author(2L, "test1"));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new Author(1L, "test"))).thenReturn(Optional.empty());
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(new Author(2L, "test1"))).thenReturn(Optional.of(new Author(2L, "update")));
        given(repository.findById(3L)).willReturn(Optional.of(new Author(3L, "testInsert")));
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getAuthorById() {
        Author author = new Author(1L, "test");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById(1L));
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void addAuthor() {
        Author author = new Author(3L, "testInsert");
        service.save(author);
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById(3L));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void getAllAuthors() {
        Author author = new Author(1L, "test");
        Author author1 = new Author(2L, "test1");
        List<Author> authors = service.getAllAuthors();
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
    }

    @Test
    @DisplayName("обновлять имя автора")
    void updateAuthor() {
        Author author = service.getAuthorById(2L);
        author.setName("update");
        service.updateAuthor(author);
        assertEquals(author.getName(), service.getAuthorById(2L).getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteAuthorById() {
        Author author = service.getAuthorById(1L);
        assertThat(author).isNotNull();
        service.deleteAuthor(author);
        author = service.getAuthorById(1L);
        assertThat(author).isNull();
    }
}