package homework2.service;

import homework2.dao.CommentDao;
import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Comment;
import homework2.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с комментариями")
@SpringBootTest(classes = CommentServiceJdbc.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentServiceJdbcTest {

    @Autowired
    private CommentService service;

    @MockBean
    private CommentDao dao;
    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        Mockito.reset(dao);
        Comment comment = new Comment(1L, "comment");
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        comment.setBook(book);

        Book book1 = new Book(2L, "book");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(1L, "genre"));
        Comment comment1 = new Comment(2L, "comment1");
        comment1.setBook(book1);
        Comment comment2 = new Comment(3L, "test");
        comment2.setBook(book);
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment1);
        Mockito.when(dao.findById(1L)).thenReturn(Optional.of(comment)).thenReturn(Optional.empty());
        given(dao.findById(2L)).willReturn(Optional.of(comment1));
        given(dao.findById(3L)).willReturn(Optional.of(comment2));
        given(dao.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("корректно добавлять комментарий")
    void addComment() {
        service.addComment(1L, new Comment("test"));
        Comment comment = new Comment(3L, "test");
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        comment.setBook(book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById(3L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void findAll() {
        Comment comment = new Comment(1L, "comment");
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        comment.setBook(book);
        Book book1 = new Book(2L, "book");
        book1.getAuthors().add(new Author(2L, "test1"));
        book1.getGenres().add(new Genre(1L, "genre"));
        Comment comment1 = new Comment(2L, "comment1");
        comment1.setBook(book1);
        List<Comment> comments = service.findAll();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий")
    void getById() {
        Comment comment = new Comment(1L, "comment");
        Book book = new Book(1L, "book");
        book.getAuthors().add(new Author(1L, "test"));
        book.getGenres().add(new Genre(2L, "genre1"));
        comment.setBook(book);
        assertThat(comment).isEqualToComparingFieldByField(service.getById(1L));
    }

    @Test
    @DisplayName("корректно удалять комментарий")
    void deleteComment() {
        Comment comment = service.getById(1L);
        assertThat(comment).isNotNull();
        service.deleteComment(comment.getId());
        comment = service.getById(1L);
        assertThat(comment).isNull();
    }
}