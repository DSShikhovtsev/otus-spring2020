package homework2.service;

import homework2.AbstractRepositoryTest;
import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service для работы с жанрами")
class GenreServiceMongoTest extends AbstractRepositoryTest {

    @Autowired
    private GenreService service;

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
    void save() {
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
    void deleteGenreById() {
        Genre genre = service.getGenreById("1");
        assertThat(genre).isNotNull();
        service.deleteGenreById("1");
        genre = service.getGenreById("1");
        assertThat(genre).isNull();
    }
}