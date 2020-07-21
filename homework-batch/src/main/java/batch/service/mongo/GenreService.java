package batch.service.mongo;

import batch.domain.mongo.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);
    Genre getGenreById(String id);
    List<Genre> getAllGenres();
    void deleteGenreById(String id);
    void updateGenre(Genre genre);
}
