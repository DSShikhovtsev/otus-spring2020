package batch.reposinories.h2;

import batch.domain.h2.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentH2Repository extends JpaRepository<Comment, Long> {
}
