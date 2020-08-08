package hystrix.service.genre;

import hystrix.domain.Genre;
import hystrix.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceMongo implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceMongo(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(String id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteGenreById(String id) {
        genreRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateGenre(Genre genre) {
        genreRepository.save(genre);
    }
}
