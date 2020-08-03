package docker.service;

import docker.domain.Genre;
import docker.repository.GenreRepository;
import docker.service.genre.GenreService;
import docker.service.genre.GenreServiceMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с жанрами")
@SpringBootTest(classes = GenreServiceMongo.class)
class GenreServiceMongoTest {

    @Autowired
    private GenreService service;

    @MockBean
    private GenreRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        List<Genre> list = new ArrayList<>();
        list.add(new Genre("1", "genre"));
        list.add(new Genre("2", "genre1"));
        Mockito.when(repository.findById("1")).thenReturn(Optional.of(new Genre("1", "genre"))).thenReturn(Optional.empty());
        given(repository.findById("2")).willReturn(Optional.of(new Genre("2", "genre1")));
        given(repository.findById("3")).willReturn(Optional.of(new Genre("3", "test")));
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        Genre genre = new Genre("1", "genre");
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById("1"));
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void getAllGenres() {
        Genre genre = new Genre("1", "genre");
        Genre genre1 = new Genre("2", "genre1");
        List<Genre> genres = service.getAllGenres();
        assertThat(genres).hasSize(2).containsExactlyInAnyOrder(genre, genre1);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void addGenre() {
        service.save(new Genre("3", "test"));
        Genre genre = service.getGenreById("3");
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById("3"));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void updateGenre() {
        Genre genre = service.getGenreById("2");
        genre.setDescription("update");
        service.updateGenre(genre);
        assertEquals(genre.getDescription(), service.getGenreById("2").getDescription());
    }

    @Test
    @DisplayName("удалять жанр")
    void deleteGenreById() {
        Genre genre = service.getGenreById("1");
        assertThat(genre).isNotNull();
        service.deleteGenreById("1");
        genre = service.getGenreById("1");
        assertThat(genre).isNull();
    }
}