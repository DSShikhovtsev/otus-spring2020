package homework3.controller;

import homework3.domain.Author;
import homework3.repository.AuthorRepository;
import homework3.repository.BookRepository;
import homework3.repository.CommentRepository;
import homework3.repository.GenreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Controller для работы с авторами")
@RunWith(SpringRunner.class)
@WebFluxTest
public class AuthorControllerTest  {

    @Autowired
    @Qualifier("authorRouter")
    private RouterFunction router;

    private WebTestClient client;
    @MockBean
    private AuthorRepository repository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Before
    public void setUp() {
        client = WebTestClient.bindToRouterFunction(router).build();

        Mockito.reset(repository);
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "author1");
        Author author2 = new Author("3", "testInsert");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        given(repository.findAll()).willReturn(Flux.just(author, author1));
        given(repository.findById("1")).willReturn(Mono.just(author));
        given(repository.findById("2")).willReturn(Mono.just(new Author("2", "test")));
        given(repository.findById("3")).willReturn(Mono.just(author2));
        given(repository.save(any())).willReturn(Mono.just(author2));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    public void showAuthors() {
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "author1");
        client.get()
                .uri("/flux/showAuthors")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Author.class)
                .contains(author, author1);
    }

    @Test
    @DisplayName("возвращать ожидаемого автора")
    public void showAuthor() {
        Author author = new Author("1", "author");
        client.get()
                .uri("/flux/author/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Author.class)
                .value(response ->
                        assertThat(response).isEqualToComparingFieldByField(author));
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    public void addAuthor() {
        Author author = new Author("3", "testInsert");
        client.post()
                .uri("/flux/author")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(author), Author.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Author.class)
                .value(response -> assertThat(response).isEqualToComparingFieldByField(author));
    }
}