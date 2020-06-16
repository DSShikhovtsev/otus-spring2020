package homework3.controller;

import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Genre;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DisplayName("Controller для работы с жанрами")
@RunWith(SpringRunner.class)
@WebFluxTest
public class GenreControllerTest {

    @Autowired
    @Qualifier("genreRouter")
    private RouterFunction router;

    private WebTestClient client;
    @MockBean
    private GenreRepository repository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Before
    public void setUp() {
        client = WebTestClient.bindToRouterFunction(router).build();

        Mockito.reset(repository);
        Genre genre = new Genre("1", "genre");
        Genre genre1 = new Genre("2", "genre1");
        Genre genre2 = new Genre("3", "testInsert");
        List<Genre> list = new ArrayList<>();
        list.add(genre);
        list.add(genre1);
        given(repository.findAll()).willReturn(Flux.just(genre, genre1));
        given(repository.findById("1")).willReturn(Mono.just(genre));
        given(repository.findById("2")).willReturn(Mono.just(new Genre("2", "test")));
        given(repository.findById("3")).willReturn(Mono.just(genre2));
    }

    @Test
    @DisplayName("возвращать список всех жанров")
    public void showGenres() {
        Genre genre = new Genre("1", "genre");
        Genre genre1 = new Genre("2", "genre1");
        client.get()
                .uri("/flux/showGenres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Genre.class)
                .contains(genre, genre1);
    }

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    public void showGenre() {
        Genre genre = new Genre("1", "genre");
        client.get()
                .uri("/flux/genre/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Genre.class)
                .value(response ->
                        assertThat(response).isEqualToComparingFieldByField(genre));
    }
}