package homework3.service;

import homework3.domain.Genre;
import homework3.repository.GenreRepository;
import homework3.service.genre.GenreService;
import homework3.service.genre.GenreServiceJpa;
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
@SpringBootTest(classes = GenreServiceJpa.class)
class GenreServiceJpaTest {

    @Autowired
    private GenreService service;

    @MockBean
    private GenreRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        List<Genre> list = new ArrayList<>();
        list.add(new Genre(1L, "genre"));
        list.add(new Genre(2L, "genre1"));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new Genre(1L, "genre"))).thenReturn(Optional.empty());
        given(repository.findById(2L)).willReturn(Optional.of(new Genre(2L, "genre1")));
        given(repository.findById(3L)).willReturn(Optional.of(new Genre(3L, "test")));
        given(repository.findAll()).willReturn(list);
    }

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        Genre genre = new Genre(1L, "genre");
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void getAllGenres() {
        Genre genre = new Genre(1L, "genre");
        Genre genre1 = new Genre(2L, "genre1");
        List<Genre> genres = service.getAllGenres();
        assertThat(genres).hasSize(2).containsExactlyInAnyOrder(genre, genre1);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void addGenre() {
        service.save(new Genre(3L, "test"));
        Genre genre = service.getGenreById(3L);
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(3L));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void updateGenre() {
        Genre genre = service.getGenreById(2L);
        genre.setDescription("update");
        service.updateGenre(genre);
        assertEquals(genre.getDescription(), service.getGenreById(2L).getDescription());
    }

    @Test
    @DisplayName("удалять жанр")
    void deleteGenreById() {
        Genre genre = service.getGenreById(1L);
        assertThat(genre).isNotNull();
        service.deleteGenre(genre);
        genre = service.getGenreById(1L);
        assertThat(genre).isNull();
    }
}