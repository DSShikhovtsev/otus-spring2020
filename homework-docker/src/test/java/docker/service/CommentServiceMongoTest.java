package docker.service;

import docker.domain.Author;
import docker.domain.Book;
import docker.domain.Comment;
import docker.domain.Genre;
import docker.repository.CommentRepository;
import docker.service.book.BookService;
import docker.service.comment.CommentService;
import docker.service.comment.CommentServiceMongo;
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
@SpringBootTest(classes = CommentServiceMongo.class)
class CommentServiceMongoTest {

    @Autowired
    private CommentService service;

    @MockBean
    private CommentRepository repository;
    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
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
        Mockito.when(repository.findById("1")).thenReturn(Optional.of(comment)).thenReturn(Optional.empty());
        given(repository.findById("2")).willReturn(Optional.of(comment1));
        given(repository.findById("3")).willReturn(Optional.of(comment2));
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий")
    void getById() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("1", "comment", book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById("1"));
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void findAll() {
        Comment comment = service.getById("1");
        Comment comment1 = service.getById("2");
        List<Comment> comments = service.getAllComments();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    @DisplayName("добавлять комментарий")
    void addComment() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        service.addComment("1", new Comment("3", "testInsert"));
        Comment comment = new Comment("3", "testInsert", book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById("3"));
    }

    @Test
    @DisplayName("удалять комментарий")
    void deleteComment() {
        Comment comment = service.getById("1");
        assertThat(comment).isNotNull();
        service.deleteComment(comment.getId());
        comment = service.getById("1");
        assertThat(comment).isNull();
    }
}