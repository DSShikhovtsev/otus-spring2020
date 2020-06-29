package homework3.service.comment;

import homework3.domain.Comment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CommentService {

    @PreAuthorize("hasPermission(#comment, 'WRITE')")
    Comment addComment(Long bookId, Comment comment);

    @PreAuthorize("hasPermission(#comment, 'WRITE')")
    void deleteComment(Comment comment);

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Comment> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Comment getById(Long id);
    void deleteByBookId(Long id);
}
