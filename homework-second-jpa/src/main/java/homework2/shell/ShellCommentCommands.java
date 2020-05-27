package homework2.shell;

import homework2.domain.Comment;
import homework2.service.CommentUtilService;
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
    public void addComment(@ShellOption Long id, @ShellOption String comment) {
        commentService.addComment(id, new Comment(comment));
    }

    @ShellMethod(value = "delete comment", key = {"dC", "delC", "deleteComment"})
    public void deleteComment(@ShellOption Long commentId) {
        commentService.deleteComment(commentId);
    }

    @ShellMethod(value = "show all comments", key = {"shC", "showComments"})
    public void showAllComments() {
        System.out.println(commentService.findAll());
    }

    @ShellMethod(value = "show comment", key = {"sC", "showComment"})
    public void showComment(@ShellOption Long commentId) {
        System.out.println(commentService.getById(commentId));
    }
}