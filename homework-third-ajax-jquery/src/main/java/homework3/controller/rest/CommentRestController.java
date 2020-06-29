package homework3.controller.rest;

import homework3.domain.Comment;
import homework3.service.comment.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService service;

    public CommentRestController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/api/showComments")
    public List<Comment> showComments() {
        return service.getAllComments();
    }

    @GetMapping("/api/comment")
    public Comment showComment(@RequestParam("id") String id) {
        return service.getById(id);
    }

    @PostMapping("/api/comment")
    public void saveComment(@RequestBody Comment comment, @RequestParam String bookId) {
        service.addComment(bookId, comment);
    }
}
