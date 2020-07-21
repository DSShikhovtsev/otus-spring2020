package batch.shell;

import batch.domain.mongo.Comment;
import batch.service.mongo.CommentUtilService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommentCommands {

    private final CommentUtilService commentService;

    public ShellCommentCommands(CommentUtilService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "add comment", key = {"aC", "addC", "addComment"})
    public void addComment(@ShellOption String id, @ShellOption String comment) {
        commentService.addComment(id, new Comment(comment));
    }

    @ShellMethod(value = "delete comment", key = {"dC", "delC", "deleteComment"})
    public void deleteComment(@ShellOption String commentId) {
        commentService.deleteComment(commentId);
    }

    @ShellMethod(value = "show all comments", key = {"shC", "showComments"})
    public void showAllComments() {
        System.out.println(commentService.findAll());
    }

    @ShellMethod(value = "show comment", key = {"sC", "showComment"})
    public void showComment(@ShellOption String commentId) {
        System.out.println(commentService.getById(commentId));
    }
}
