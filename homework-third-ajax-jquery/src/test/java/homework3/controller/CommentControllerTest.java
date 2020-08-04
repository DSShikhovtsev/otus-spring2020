package homework3.controller;

import homework3.domain.Author;
import homework3.domain.Book;
import homework3.domain.Comment;
import homework3.domain.Genre;
import homework3.service.comment.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller для работы с комментариями")
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CommentService service;

    @BeforeEach
    void setUp() {
        Mockito.reset(service);
        Comment comment = new Comment("1", "comment");
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        comment.setBook(book);

        Book book1 = new Book("2", "book1");
        book1.getAuthors().add(new Author("2", "test1"));
        book1.getGenres().add(new Genre("2", "genre1"));
        Comment comment1 = new Comment("2", "comment1");
        comment1.setBook(book1);
        Comment comment2 = new Comment("3", "testInsert");
        comment2.setBook(book);
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment1);
        Mockito.when(service.getById("1")).thenReturn(comment);
        given(service.getById("2")).willReturn(comment1);
        given(service.getById("3")).willReturn(comment2);
        given(service.getAllComments()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий")
    void showComment() throws Exception {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("1", "comment", book);
        Comment returned = (Comment) Objects.requireNonNull(this.mvc.perform(get("/comment?id=1")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("comment");
        assertEquals(comment, returned);
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void showComments() throws Exception {
        Comment comment = service.getById("1");
        Comment comment1 = service.getById("2");
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment1);
        String commentList = Objects.requireNonNull(this.mvc.perform(get("/showComments")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("comments").toString();
        assertEquals(commentList, list.toString());
    }

    @Test
    @DisplayName("добавлять комментарий")
    void addComment() throws Exception {
        Book book = new Book("1", "book");
        book.getAuthors().add(new Author("1", "test"));
        book.getGenres().add(new Genre("1", "genre"));
        Comment comment = new Comment("3", "testInsert", book);
        this.mvc.perform(post("/comment?id=&comment=testInsert&idBook=1")).andExpect(status().isFound());
        List<Comment> returned = (List<Comment>) Objects.requireNonNull(this.mvc.perform(get("/showComments")).andReturn()
                .getModelAndView()).getModelMap().getAttribute("comments");
        assertThat(returned.contains(comment));
    }

    @Test
    @DisplayName("удалять комментарий")
    void deleteComment() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .post("/commentDelete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}