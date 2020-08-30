package webflux.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.controller.rest.AuthorRestController;
import webflux.domain.Author;
import webflux.service.author.AuthorService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Controller для работы с авторами")
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = AuthorRestController.class)
@Import(AuthorService.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService service;
    @Autowired
    private WebTestClient client;

    @Before
    public void setUp() {
        Mockito.reset(service);
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "author1");
        Author author2 = new Author("3", "testInsert");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        given(service.getAllAuthors()).willReturn(Flux.just(author, author1));
        given(service.getAuthorById("1")).willReturn(Mono.just(author));
        given(service.getAuthorById("2")).willReturn(Mono.just(new Author("2", "test")));
        given(service.getAuthorById("3")).willReturn(Mono.just(author2));
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    public void showAuthors() {
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "author1");
        client.get()
                .uri("/api/authors")
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
                .uri("/api/authors/1")
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
                .uri("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(author), Author.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("удалять автора из таблицы")
    public void deleteAuthor() {
        client.delete()
                .uri("/api/authors/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}