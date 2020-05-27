package homework3.controller;

import homework3.domain.Genre;
import homework3.service.genre.GenreService;
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

@DisplayName("Controller для работы с жанрами")
@WebMvcTest(GenreController.class)
@AutoConfigureDataMongo
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private GenreService service;

    @BeforeEach
    void setUp() {
        Mockito.reset(service);
        Genre genre = new Genre("1", "genre");
        Genre genre1 = new Genre("2", "genre1");
        Genre genre2 = new Genre("3", "testInsert");
        List<Genre> list = new ArrayList<>();
        list.add(genre);
        list.add(genre1);
        given(service.getAllGenres()).willReturn(list);
        given(service.getGenreById("1")).willReturn(genre);
        given(service.getGenreById("2")).willReturn(new Genre("2", "test"));
        given(service.getGenreById("3")).willReturn(genre2);
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void showGenres() throws Exception {
        Genre genre = new Genre("1", "genre");
        Genre genre1 = new Genre("2", "genre1");
        List<Genre> list = new ArrayList<>();
        list.add(genre);
        list.add(genre1);
        String genreList = Objects.requireNonNull(this.mvc.perform(get("/showGenre")).andExpect(status().isOk()).andReturn()
                .getModelAndView()).getModelMap().getAttribute("genres").toString();
        assertEquals(genreList, list.toString());
    }

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void showGenre() throws Exception {
        Genre genre = new Genre("1", "genre");
        Genre returned = (Genre) Objects.requireNonNull(this.mvc.perform(get("/genre?id=1")).andExpect(status().isOk())
                .andReturn().getModelAndView()).getModelMap().getAttribute("genre");
        assertEquals(genre, returned);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void addGenre() throws Exception {
        Genre genre = new Genre("3", "testInsert");
        List<Genre> returned = (List<Genre>) Objects.requireNonNull(this.mvc.perform(post("/genre?id=&name=testInsert")).andExpect(status().isOk())
                .andReturn().getModelAndView()).getModelMap().getAttribute("genres");
        assertThat(returned.contains(genre));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void updateGenre() throws Exception {
        Genre genre = new Genre("2", "test");
        Genre returned = (Genre) Objects.requireNonNull(this.mvc.perform(get("/genre?id=2")).andExpect(status().isOk())
                .andReturn().getModelAndView()).getModelMap().getAttribute("genre");
        assertEquals(genre, returned);
    }

    @Test
    @DisplayName("удалять жанр")
    void deleteGenre() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .post("/genreDelete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}