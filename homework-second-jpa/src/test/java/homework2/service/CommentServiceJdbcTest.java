package homework2.service;

import homework2.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service для работы с комментариями")
@RunWith(SpringRunner.class)
@DataJpaTest
@Import({CommentServiceJdbc.class, BookServiceJdbc.class})
class CommentServiceJdbcTest {

    @InjectMocks
    @Autowired
    private CommentServiceJdbc service;

    @Test
    @DisplayName("корректно добавлять комментарий")
    void addComment() {
        service.addComment(1L, new Comment("test"));
        Comment comment = new Comment(3L, "test");
        assertThat(comment).isEqualToComparingFieldByField(service.getById(3L));
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
    void getById() {
        Comment comment = new Comment(1L, "comment");
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