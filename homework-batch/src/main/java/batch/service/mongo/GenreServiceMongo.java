package batch.service.mongo;

import batch.reposinories.mongo.GenreMongoRepository;
import batch.domain.mongo.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceMongo implements GenreService {

    private final GenreMongoRepository genreMongoRepository;

    public GenreServiceMongo(GenreMongoRepository genreMongoRepository) {
        this.genreMongoRepository = genreMongoRepository;
    }

    @Transactional
    @Override
    public void save(Genre genre) {
        genreMongoRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(String id) {
        return genreMongoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return genreMongoRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteGenreById(String id) {
        genreMongoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateGenre(Genre genre) {
        genreMongoRepository.save(genre);
    }
}
