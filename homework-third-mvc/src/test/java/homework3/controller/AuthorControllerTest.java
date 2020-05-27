package homework3.controller;

import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Controller для работы с авторами")
@WebMvcTest(AuthorController.class)
@AutoConfigureDataMongo
class AuthorControllerTest  {

    @Autowired
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
        String authorList = Objects.requireNonNull(this.mvc.perform(get("/showAuthor")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("authors").toString();
        assertEquals(authorList, list.toString());
    }

    @Test
    @DisplayName("возвращать ожидаемого автора")
    void showAuthor() throws Exception {
        Author author = new Author("1", "author");
        Author returned = (Author) Objects.requireNonNull(this.mvc.perform(get("/author?id=1")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("author");
        assertEquals(author, returned);
    }

    @Test
    @DisplayName("добавлять автора в таблицу")
    void addAuthor() throws Exception {
        Author author = new Author("3", "testInsert");
        List<Author> returned = (List<Author>) Objects.requireNonNull(this.mvc.perform(post("/author?id=&name=testInsert")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("authors");
        assertThat(returned.contains(author));
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
        this.mvc.perform(MockMvcRequestBuilders
                .post("/authorDelete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}