package homework2.dao;

import homework2.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentDao extends CrudRepository<Comment, Long> {

    List<Comment> findAll();
}
