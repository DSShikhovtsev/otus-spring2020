package homework2.service;

import homework2.dao.AuthorDao;
import homework2.domain.Author;
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
@SpringBootTest(classes = AuthorServiceJdbc.class)
class AuthorServiceJdbcTest {

    @Autowired
    private AuthorService service;

    @MockBean
    private AuthorDao dao;

    @BeforeEach
    void setUp() {
        Mockito.reset(dao);
        List<Author> list = new ArrayList<>();
        list.add(new Author(1L, "test"));
        list.add(new Author(2L, "test1"));
        Mockito.when(dao.findById(1L)).thenReturn(Optional.of(new Author(1L, "test"))).thenReturn(Optional.empty());
        given(dao.findById(2L)).willReturn(Optional.of(new Author(2L, "test1")));
        given(dao.findById(3L)).willReturn(Optional.of(new Author(3L, "testInsert")));
        given(dao.findAll()).willReturn(list);
    }

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
        List<Author> authors = service.getAllAuthors();
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
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