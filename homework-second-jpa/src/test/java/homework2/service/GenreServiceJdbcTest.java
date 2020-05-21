package homework2.service;

import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service для работы с жанрами")
@SpringBootTest
class GenreServiceJdbcTest {

    @Autowired
    private GenreServiceJdbc service;

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        Genre genre = new Genre(1L, "genre");
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void findAll() {
        Genre genre = new Genre(1L, "genre");
        Genre genre1 = new Genre(2L, "genre1");
        Genre genre2 = new Genre(3L, "test");
        List<Genre> genres = service.getAllGenres();
        assertThat(genres).hasSize(3).containsExactlyInAnyOrder(genre, genre1, genre2);
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void insert() {
        service.save(new Genre("test"));
        Genre genre = service.getGenreById(3L);
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(3L));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void update() {
        Genre genre = service.getGenreById(2L);
        genre.setDescription("update");
        service.updateGenre(genre);
        assertEquals(genre.getDescription(), service.getGenreById(2L).getDescription());
    }

    @Test
    @DisplayName("удалять жанр из таблицы")
    void deleteById() {
        Genre genre = service.getGenreById(1L);
        assertThat(genre).isNotNull();
        service.deleteGenreById(1L);
        genre = service.getGenreById(1L);
        assertThat(genre).isNull();
    }
}