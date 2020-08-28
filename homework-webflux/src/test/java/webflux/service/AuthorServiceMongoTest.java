package webflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.domain.Author;
import webflux.repository.AuthorRepository;
import webflux.service.author.AuthorService;
import webflux.service.author.AuthorServiceMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с авторами")
@SpringBootTest(classes = AuthorServiceMongo.class)
public class AuthorServiceMongoTest {

    @Autowired
    private AuthorService service;

    @MockBean
    private AuthorRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        Author author = new Author("1", "test");
        Author author1 = new Author("2", "test1");
        Author update = new Author("2", "update");
        Author author3 = new Author("3", "testInsert");
        Mockito.when(repository.findById("1")).thenReturn(Mono.just(author)).thenReturn(Mono.empty());
        Mockito.when(repository.findById("2")).thenReturn(Mono.just(author1)).thenReturn(Mono.just(update));
        given(repository.findById("3")).willReturn(Mono.just(author3));
        given(repository.deleteAuthorById("1")).willReturn(Mono.empty());
        given(repository.save(update)).willReturn(Mono.just(update));
        given(repository.save(author3)).willReturn(Mono.just(author3));
        given(repository.findAll()).willReturn(Flux.just(author, author1));
    }

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void getAuthorById() {
        Author author = new Author("1", "test");
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById("1").block());
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void addAuthor() {
        Author author = new Author("3", "testInsert");
        service.save(author);
        assertThat(author).isEqualToComparingFieldByField(service.getAuthorById("3").block());
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void getAllAuthors() {
        Author author = new Author("1", "test");
        Author author1 = new Author("2", "test1");
        List<Author> authors = service.getAllAuthors().toStream().collect(Collectors.toList());
        assertThat(authors).hasSize(2).containsExactlyInAnyOrder(author, author1);
    }

    @Test
    @DisplayName("обновлять имя автора")
    void updateAuthor() {
        Author author = service.getAuthorById("2").block();
        author.setName("update");
        service.updateAuthor(author);
        assertEquals(author.getName(), service.getAuthorById("2").block().getName());
    }

    @Test
    @DisplayName("удалять автора")
    void deleteAuthorById() {
        Author author = service.getAuthorById("1").block();
        assertThat(author).isNotNull();
        service.deleteAuthorById("1");
        author = service.getAuthorById("1").block();
        assertThat(author).isNull();
    }
}