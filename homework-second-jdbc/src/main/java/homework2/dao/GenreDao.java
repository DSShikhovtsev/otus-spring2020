package homework2.dao;

import homework2.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> findAll();
    void deleteById(Long id);
    void update(Genre genre);
}
