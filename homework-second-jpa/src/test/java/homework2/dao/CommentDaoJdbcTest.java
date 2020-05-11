package homework2.dao;

import homework2.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с комментариями")
@RunWith(SpringRunner.class)
@DataJpaTest
class CommentDaoJdbcTest {

    @Autowired
    private CommentDao dao;

    @Test
    @DisplayName("корректно добавлять комментарий")
    void addComment() {
        dao.save(new Comment("test"));
        Comment comment = dao.findById(3L).orElse(null);
        assertThat(comment).isEqualToComparingFieldByField(dao.findById(3L).get());
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void findAll() {
        Comment comment = dao.findById(1L).orElse(null);
        Comment comment1 = dao.findById(2L).orElse(null);
        List<Comment> comments = dao.findAll();
        assertThat(comments).hasSize(2).containsExactlyInAnyOrder(comment, comment1);
    }

    @Test
    void getById() {
        Comment comment = new Comment(1L, "comment");
        assertThat(comment).isEqualToComparingFieldByField(dao.findById(1L).get());
    }

    @Test
    @DisplayName("корректно удалять комментарий")
    void deleteComment() {
        Comment comment = dao.findById(1L).get();
        assertThat(comment).isNotNull();
        dao.deleteById(comment.getId());
        comment = dao.findById(1L).orElse(null);
        assertThat(comment).isNull();
    }
}