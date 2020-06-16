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

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Controller для работы с книгами")
@RunWith(SpringRunner.class)
@WebFluxTest
public class BookControllerTest {

    @Autowired
    @Qualifier("bookRouter")
    private RouterFunction router;

    private WebTestClient client;
    @MockBean
    private BookRepository repository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Before
    public void setUp() {
        client = WebTestClient.bindToRouterFunction(router).build();

        Mockito.reset(repository);
        List<Book> books = new ArrayList<>();
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        books.add(book);
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        books.add(book1);
        Book book2 = new Book("2", "test");
        book2.getAuthors().add(new Author("2", "test1"));
        book2.getGenres().add(new Genre("2", "genre1"));
        given(repository.findById("1")).willReturn(Mono.just(book));
        given(repository.findById("2")).willReturn(Mono.just(book2));
        given(repository.findById("3")).willReturn(Mono.just(new Book("3", "testInsert")));
        given(repository.findAll()).willReturn(Flux.just(book, book1));
    }

    @Test
    @DisplayName("возвращать список всех книг")
    public void showBooks() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        client.get()
                .uri("/flux/showBooks")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .contains(book, book1);
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу")
    public void showBook() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        client.get()
                .uri("/flux/book/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(response ->
                        assertThat(response).isEqualToComparingFieldByField(book));
    }
}