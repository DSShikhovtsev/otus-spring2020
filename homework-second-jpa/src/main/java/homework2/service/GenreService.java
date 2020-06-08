package homework2.service;

import homework2.domain.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);
    Genre getGenreById(Long id);
    List<Genre> getAllGenres();
    void deleteGenreById(Long id);
    void updateGenre(Genre genre);
}
