package batch.service.h2;

import batch.domain.h2.Genre;
import batch.reposinories.h2.GenreH2Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceJdbc implements GenreService {

    private final GenreH2Repository repository;

    public GenreServiceJdbc(GenreH2Repository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(Genre genre) {
        repository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void deleteGenreById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateGenre(Genre genre) {
        repository.save(genre);
    }
}
