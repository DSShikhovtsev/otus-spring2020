package batch.service;

import batch.domain.mongo.Author;
import batch.domain.mongo.Book;
import batch.domain.mongo.Comment;
import batch.domain.mongo.Genre;
import batch.reposinories.mongo.CommentMongoRepository;
import batch.service.mongo.BookService;
import batch.service.mongo.CommentService;
import batch.service.mongo.CommentServiceMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с комментариями")
@SpringBootTest(classes = CommentServiceMongo.class)
class CommentServiceMongoTest {

    @Autowired
    private CommentService service;

    @MockBean
    private CommentMongoRepository repository;
    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        Comment comment = new Comment("1", "comment");
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "author"));
        book.getGenres().add(new Genre("1", "genre"));
        comment.setBook(book);

        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "author1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        Comment comment1 = new Comment("2", "comment1");
        comment1.setBook(book1);
        Comment comment2 = new Comment("3", "testInsert");
        comment2.setBook(book);
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment1);
        Mockito.when(repository.findById("1")).thenReturn(comment).thenReturn(null);
        given(repository.findById("2")).willReturn(comment1);
        given(repository.findById("3")).willReturn(comment2);
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий")
    void getById() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "author"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("1", "comment", book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById("1"));
    }

    @Test
    void findAll() {
        Comment comment = service.getById("1");
        Comment comment1 = service.getById("2");
        List<Comment> comments = service.findAll();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    void addComment() {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "author"));
        book.getGenres().add(new Genre("1", "genre"));
        service.addComment("1", new Comment("3", "testInsert"));
        Comment comment = new Comment("3", "testInsert", book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById("3"));
    }

    @Test
    void deleteComment() {
        Comment comment = service.getById("1");
        assertThat(comment).isNotNull();
        service.deleteComment(comment.getId());
        comment = service.getById("1");
        assertThat(comment).isNull();
    }
}