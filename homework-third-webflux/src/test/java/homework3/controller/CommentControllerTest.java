package homework3.controller;

import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Comment;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Controller для работы с комментариями")
@RunWith(SpringRunner.class)
@WebFluxTest
public class CommentControllerTest {

    @Autowired
    @Qualifier("commentRouter")
    private RouterFunction router;

    private WebTestClient client;
    @MockBean
    private CommentRepository repository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private GenreRepository genreRepository;

    @Before
    public void setUp() {
        client = WebTestClient.bindToRouterFunction(router).build();

        Mockito.reset(repository);
        Comment comment = new Comment("1", "comment");
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        comment.setBook(book);

        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        Comment comment1 = new Comment("2", "comment1");
        comment1.setBook(book1);
        Comment comment2 = new Comment("3", "testInsert");
        comment2.setBook(book);
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment1);
        given(repository.findAll()).willReturn(Flux.just(comment, comment1));
        given(repository.findById("1")).willReturn(Mono.just(comment));
        given(repository.findById("2")).willReturn(Mono.just(comment1));
        given(repository.findById("3")).willReturn(Mono.just(comment2));
        given(repository.save(comment2)).willReturn(Mono.empty());
        given(repository.deleteById("1")).willReturn(Mono.empty());
    }

    @Test
    @DisplayName("возвращать список всех комментариев")
    public void showComments() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("1", "comment", book);
        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        Comment comment1 = new Comment("2", "comment1", book1);
        client.get()
                .uri("/flux/showComments")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Comment.class)
                .contains(comment, comment1);
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий")
    public void showComment() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("1", "comment", book);
        client.get()
                .uri("/flux/comment/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Comment.class)
                .value(response ->
                        assertThat(response).isEqualToComparingFieldByField(comment));
    }

    @Test
    @DisplayName("добавлять комментарий в таблицу")
    public void addComment() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("3", "testInsert", book);
        client.post()
                .uri("/flux/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(comment), Comment.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("удалять комментарий из таблицы")
    public void deleteComment() {
        client.post()
                .uri("/flux/deleteComment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}