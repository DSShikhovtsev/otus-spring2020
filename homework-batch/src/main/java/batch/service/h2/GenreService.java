package batch.service.h2;


import batch.domain.h2.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);
    Genre getGenreById(Long id);
    List<Genre> getAllGenres();
    void deleteGenreById(Long id);
    void updateGenre(Genre genre);
}
