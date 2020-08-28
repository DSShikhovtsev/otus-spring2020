package webflux.controller;

import com.google.gson.Gson;
import webflux.domain.Author;
import webflux.service.author.AuthorService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller для работы с авторами")
@WebMvcTest(AuthorController.class)
class AuthorControllerTest  {

    /*@Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorService service;

    @BeforeEach
    private void setUp() {
        Mockito.reset(service);
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "author1");
        Author author2 = new Author("3", "testInsert");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        given(service.getAllAuthors()).willReturn(list);
        given(service.getAuthorById("1")).willReturn(author);
        given(service.getAuthorById("2")).willReturn(new Author("2", "test"));
        given(service.getAuthorById("3")).willReturn(author2);
    }

    @Test
    @DisplayName("возвращать список всех авторов")
    void showAuthors() throws Exception {
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "author1");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        String json = new Gson().toJson(list);
        this.mvc.perform(get("/api/authors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void showAuthor() throws Exception {
        Author author = new Author("1", "author");
        String json = new Gson().toJson(author);this.mvc.perform(get("/api/authors/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void addAuthor() throws Exception {
        Author author = new Author("3", "testInsert");
        String json = new Gson().toJson(author);
        this.mvc.perform(post("/api/authors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

    }

    @Test
    @DisplayName("обновлять имя автора")
    void updateAuthor() throws Exception {
        Author author = new Author("2", "test");
        Author returned = (Author) Objects.requireNonNull(this.mvc.perform(get("/author?id=2")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("author");
        assertEquals(author, returned);
    }

    @Test
    @DisplayName("удалять автора")
    void deleteAuthor() throws Exception {
        String json = new Gson().toJson(new Author("2", "author1"));
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/api/authors/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }*/
}