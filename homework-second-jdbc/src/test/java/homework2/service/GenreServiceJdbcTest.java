package homework2.service;

import homework2.dao.BooksGenreJdbc;
import homework2.dao.GenreDaoJdbc;
import homework2.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с жанрами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({GenreServiceJdbc.class, GenreDaoJdbc.class, BooksGenreJdbc.class})
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
    void getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "genre"));
        genres.add(new Genre(2L, "genre1"));
        assertEquals(genres.toString(), service.getAllGenres().toString());
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void putNewGenre() {
        service.putNewGenre(new Genre("test"));
        Genre genre = new Genre(3L, "test");
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(3L));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void deleteGenreById() {
        Genre genre = new Genre(2L, "update");
        service.updateGenre(genre);
        assertThat(genre).isEqualToComparingFieldByField(service.getGenreById(2L));
    }

    @Test
    @DisplayName("удалять жанр из таблицы")
    void updateGenre() {
        service.deleteGenreById(1L);
        assertThrows(Exception.class, () -> service.getGenreById(1L));
    }
}