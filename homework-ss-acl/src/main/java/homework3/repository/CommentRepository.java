package homework3.repository;

import homework3.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteByBookId(Long bookId);
}
