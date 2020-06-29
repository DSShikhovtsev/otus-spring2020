package homework3.controller;

import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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

@DisplayName("Controller для работы с авторами")
@WebMvcTest(AuthorController.class)
class AuthorControllerTest  {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorService service;

    @BeforeEach
    private void setUp() {
        Mockito.reset(service);
        Author author = new Author(1L, "author");
        Author author1 = new Author(2L, "author1");
        Author author2 = new Author(3L, "testInsert");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        given(service.getAllAuthors()).willReturn(list);
        given(service.getAuthorById(1L)).willReturn(author);
        given(service.getAuthorById(2L)).willReturn(new Author(2L, "test"));
        given(service.getAuthorById(3L)).willReturn(author2);
    }

    @WithMockUser
    @Test
    @DisplayName("возвращать список всех авторов")
    void showAuthors() throws Exception {
        Author author = new Author(1L, "author");
        Author author1 = new Author(2L, "author1");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        String authorList = Objects.requireNonNull(this.mvc.perform(get("/showAuthors")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("authors").toString();
        assertEquals(authorList, list.toString());
    }

    @WithMockUser
    @Test
    @DisplayName("возвращать ожидаемого автора")
    void showAuthor() throws Exception {
        Author author = new Author(1L, "author");
        Author returned = (Author) Objects.requireNonNull(this.mvc.perform(get("/author?id=1")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("author");
        assertEquals(author, returned);
    }

    @WithMockUser
    @Test
    @DisplayName("добавлять автора в таблицу")
    void addAuthor() throws Exception {
        Author author = new Author(3L, "testInsert");
        this.mvc.perform(post("/author?id=&name=testInsert")).andExpect(status().isFound());
        List<Author> returned = (List<Author>) Objects.requireNonNull(this.mvc.perform(get("/showAuthors")).andReturn()
                .getModelAndView()).getModelMap().getAttribute("authors");
        assertThat(returned.contains(author));
    }

    @WithMockUser
    @Test
    @DisplayName("обновлять имя автора")
    void updateAuthor() throws Exception {
        Author author = new Author(2L, "test");
        Author returned = (Author) Objects.requireNonNull(this.mvc.perform(get("/author?id=2")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("author");
        assertEquals(author, returned);
    }

    @WithMockUser
    @Test
    @DisplayName("удалять автора")
    void deleteAuthor() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .post("/authorDelete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}