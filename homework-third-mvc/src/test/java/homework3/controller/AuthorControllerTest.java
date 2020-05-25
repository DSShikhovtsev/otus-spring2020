package homework3.controller;

import homework3.AbstractControllerTest;
import homework3.AbstractServiceTest;
import homework3.domain.Author;
import homework3.service.author.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller для работы с авторами")
class AuthorControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorService service;

    @Test
    void showAuthors() throws Exception {
        Author author = new Author("1", "author");
        Author author1 = new Author("2", "update");
        Author author2 = new Author("3", "testInsert");
        List<Author> list = new ArrayList<>();
        list.add(author);
        list.add(author1);
        list.add(author2);
        given(service.getAllAuthors()).willReturn(list);
        this.mvc.perform(get("/showAuthors")).andExpect(status().isOk())
                .andExpect(content().string(list.toString()));
    }

    @Test
    void showAuthor() {
    }

    @Test
    void addAuthor() {
    }

    @Test
    void saveAuthor() {
    }

    @Test
    void deleteAuthor() {
    }
}