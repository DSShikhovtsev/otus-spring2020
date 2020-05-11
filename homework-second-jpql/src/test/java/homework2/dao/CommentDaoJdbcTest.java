package homework2.dao;

import homework2.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Dao для работы с комментариями")
@DataJpaTest
@Import({CommentDaoJdbc.class})
class CommentDaoJdbcTest {

    @Autowired
    private CommentDaoJdbc dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("корректно добавлять комментарий")
    void addComment() {
        dao.addComment(1L, new Comment("test"));
        Comment comment = em.find(Comment.class, 3L);
        assertThat(comment).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("корректно удалять комментарий")
    void deleteComment() {
        Comment comment = em.find(Comment.class, 1L);
        assertThat(comment).isNotNull();
        dao.deleteComment(comment.getId());
        em.clear();
        comment = em.find(Comment.class, 1L);
        assertThat(comment).isNull();
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void findAll() {
        Comment comment = em.find(Comment.class, 1L);
        Comment comment1 = em.find(Comment.class, 2L);
        List<Comment> comments = dao.findAll();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    void getById() {
        assertThat(em.find(Comment.class, 1L)).isEqualToComparingFieldByField(dao.getById(1L));
    }
}