package hystrix.service.genre;

import hystrix.domain.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);
    Genre getGenreById(String id);
    List<Genre> getAllGenres();
    void deleteGenreById(String id);
    void updateGenre(Genre genre);
}
