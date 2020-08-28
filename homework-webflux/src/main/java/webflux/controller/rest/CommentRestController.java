package webflux.controller.rest;

import webflux.domain.Comment;
import webflux.service.comment.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService service;

    public CommentRestController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/api/comments")
    public List<Comment> showComments() {
        return service.getAllComments();
    }

    @GetMapping("/api/comments/{id}")
    public Comment showComment(@PathVariable("id") String id) {
        return service.getById(id);
    }

    @PostMapping("/api/comments")
    public void saveComment(@RequestBody Comment comment, @RequestParam String bookId) {
        service.addComment(bookId, comment);
    }

    @DeleteMapping("/api/comments/{id}")
    public void deleteComment(@PathVariable("id") String id) {
        service.deleteComment(id);
    }
}
