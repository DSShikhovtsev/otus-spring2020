package actuator.controller;

import actuator.domain.Book;
import actuator.domain.Comment;
import actuator.service.comment.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/showComment")
    public String showComments(Model model) {
        model.addAttribute("comments", service.getAllComments());
        return "commentsPage";
    }

    @GetMapping("/comment")
    public String showComment(@RequestParam("id") String id, Model model) {
        model.addAttribute("comment", service.getById(id));
        return "comment";
    }

    @GetMapping("addComment")
    public String addComment(Model model) {
        Comment comment = new Comment();
        comment.setBook(new Book());
        model.addAttribute("comment", comment);
        return "comment";
    }

    @PostMapping("/comment")
    public String saveComment(@RequestParam("idBook") String idBook, Comment comm) {
        if (comm.getId().isEmpty()) comm.setId(null);
        service.addComment(idBook, comm);
        return "redirect:/showComment";
    }

    @PostMapping("commentDelete")
    public String deleteComment(@RequestParam("id") String id) {
        service.deleteComment(id);
        return "redirect:/showComment";
    }
}
