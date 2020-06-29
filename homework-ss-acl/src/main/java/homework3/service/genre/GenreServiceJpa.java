package homework3.service.genre;

import homework3.domain.Genre;
import homework3.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceJpa implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceJpa(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteGenre(Genre genre) {
        genreRepository.deleteById(genre.getId());
    }

    @Transactional
    @Override
    public void updateGenre(Genre genre) {
        genreRepository.save(genre);
    }
}
