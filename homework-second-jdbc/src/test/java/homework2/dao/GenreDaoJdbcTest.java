package homework2.dao;

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
@Import({GenreDaoJdbc.class, BooksGenreJdbc.class})
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc dao;

    @Test
    @DisplayName("возвращать ожидаемый жанр")
    void getGenreById() {
        Genre genre = new Genre(1L, "genre");
        assertThat(genre).isEqualToComparingFieldByField(dao.getById(1L));
    }

    @Test
    @DisplayName("возвращать ожидаемый список жанров")
    void findAll() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "genre"));
        genres.add(new Genre(2L, "genre1"));
        assertEquals(genres.toString(), dao.findAll().toString());
    }

    @Test
    @DisplayName("добавлять жанр в таблицу")
    void insert() {
        dao.insert(new Genre("test"));
        Genre genre = new Genre(3L, "test");
        assertThat(genre).isEqualToComparingFieldByField(dao.getById(3L));
    }

    @Test
    @DisplayName("обновлять описание жанра")
    void update() {
        Genre genre = new Genre(2L, "update");
        dao.update(genre);
        assertThat(genre).isEqualToComparingFieldByField(dao.getById(2L));
    }

    @Test
    @DisplayName("удалять жанр из таблицы")
    void deleteById() {
        dao.deleteById(1L);
        assertThrows(Exception.class, () -> dao.getById(1L));
    }
}