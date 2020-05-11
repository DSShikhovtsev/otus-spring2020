package homework2.service;

import homework2.dao.CommentDaoJdbc;
import homework2.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service для работы с комментариями")
@DataJpaTest
@Import({CommentServiceJdbc.class, CommentDaoJdbc.class})
class CommentServiceJdbcTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentServiceJdbc service;

    @Test
    @DisplayName("корректно добавлять комментарий")
    void addComment() {
        service.addComment(1L, new Comment("test"));
        Comment comment = em.find(Comment.class, 3L);
        assertThat(comment).isEqualToComparingFieldByField(service.getById(3L));
    }

    @Test
    @DisplayName("корректно удалять комментарий")
    void deleteComment() {
        Comment comment = em.find(Comment.class, 1L);
        assertThat(comment).isNotNull();
        service.deleteComment(comment.getId());
        em.clear();
        comment = em.find(Comment.class, 1L);
        assertThat(comment).isNull();
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void findAll() {
        Comment comment = em.find(Comment.class, 1L);
        Comment comment1 = em.find(Comment.class, 2L);
        List<Comment> comments = service.findAll();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    void getById() {
        assertThat(em.find(Comment.class, 1L)).isEqualToComparingFieldByField(service.getById(1L));
    }
}