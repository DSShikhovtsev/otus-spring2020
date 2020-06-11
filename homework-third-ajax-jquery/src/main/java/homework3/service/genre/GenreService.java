package homework3.service.genre;

import homework3.domain.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);
    Genre getGenreById(String id);
    List<Genre> getAllGenres();
    void deleteGenreById(String id);
    void updateGenre(Genre genre);
}
