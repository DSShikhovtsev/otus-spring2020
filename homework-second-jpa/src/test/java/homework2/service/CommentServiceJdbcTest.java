package homework2.service;

import homework2.domain.Author;
import homework2.domain.Book;
import homework2.domain.Comment;
import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service для работы с комментариями")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentServiceJdbcTest {

    @Autowired
    private CommentServiceJdbc service;

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
        Comment comment = service.getById(1L);
        comment.setBook(new Book(1L, "book"));
        Comment comment1 = service.getById(2L);
        comment1.setBook(new Book(2L, "book1"));
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