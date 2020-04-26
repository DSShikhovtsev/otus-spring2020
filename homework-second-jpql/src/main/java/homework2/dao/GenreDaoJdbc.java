package homework2.dao;

import homework2.domain.Genre;
import homework2.mapper.GenreMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update("insert into genres(description) values(:desc)", Collections.singletonMap("desc", genre.getDescription()));
    }

    @Override
    public Genre getById(Long id) {
        return jdbc.queryForObject("select * from genres where id = (:id)", Collections.singletonMap("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    @Override
    public void deleteById(Long id) {
        deleteByGenreId(id);
        jdbc.update("delete from genres where id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("description", genre.getDescription());
        jdbc.update("update genres set description = :description where id = :id", params);
    }

    private void deleteByGenreId(Long id) {
        jdbc.update("delete from books_genres where id_genre = :genreId", Collections.singletonMap("genreId", id));
    }
}
