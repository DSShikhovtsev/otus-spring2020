package homework3.service;

import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Comment;
import homework3.domain.Genre;
import homework3.repository.CommentRepository;
import homework3.service.book.BookService;
import homework3.service.comment.CommentService;
import homework3.service.comment.CommentServiceJpa;
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
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с комментариями")
@SpringBootTest(classes = CommentServiceJpa.class)
class CommentServiceJpaTest {

    @Autowired
    private CommentService service;

    @MockBean
    private CommentRepository repository;
    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        Comment comment = new Comment(1L, "comment");
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(1L, "genre"));
        comment.setBook(book);

        Book book1 = new Book(2L, "book1");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(2L, "genre1"));
        Comment comment1 = new Comment(2L, "comment1");
        comment1.setBook(book1);
        Comment comment2 = new Comment(3L, "testInsert");
        comment2.setBook(book);
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment1);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(comment)).thenReturn(Optional.empty());
        given(repository.findById(2L)).willReturn(Optional.of(comment1));
        given(repository.findById(3L)).willReturn(Optional.of(comment2));
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий")
    void getById() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(1L, "genre"));
        Comment comment = new Comment(1L, "comment", book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void findAll() {
        Comment comment = service.getById(1L);
        Comment comment1 = service.getById(2L);
        List<Comment> comments = service.findAll();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    @DisplayName("добавлять комментарий")
    void addComment() {
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(1L, "genre"));
        service.addComment(1L, new Comment(3L, "testInsert"));
        Comment comment = new Comment(3L, "testInsert", book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById(3L));
    }

    @Test
    @DisplayName("удалять комментарий")
    void deleteComment() {
        Comment comment = service.getById(1L);
        assertThat(comment).isNotNull();
        service.deleteComment(comment);
        comment = service.getById(1L);
        assertThat(comment).isNull();
    }
}