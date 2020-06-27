package homework3.service.genre;

import homework3.domain.Genre;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface GenreService {

    @PreAuthorize("hasPermission(#genre, 'WRITE')")
    void save(Genre genre);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Genre getGenreById(Long id);

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Genre> getAllGenres();

    @PreAuthorize("hasPermission(#genre, 'WRITE')")
    void deleteGenre(Genre genre);

    @PreAuthorize("hasPermission(#genre, 'WRITE')")
    void updateGenre(Genre genre);
}
