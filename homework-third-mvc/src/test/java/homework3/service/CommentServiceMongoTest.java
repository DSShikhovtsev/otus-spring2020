package homework3.service;

import homework3.AbstractServiceTest;
import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Comment;
import homework3.domain.Genre;
import homework3.service.comment.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service для работы с комментариями")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentServiceMongoTest extends AbstractServiceTest {

    @Autowired
    private CommentService service;

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
        List<Comment> comments = service.getAllComments();
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