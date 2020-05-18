package homework2.service;

import homework2.dao.GenreDao;
import homework2.domain.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceJdbc implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceJdbc(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Transactional
    @Override
    public void save(Genre genre) {
        genreDao.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(String id) {
        return genreDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Transactional
    @Override
    public void deleteGenreById(String id) {
        genreDao.deleteById(id);
    }

    @Transactional
    @Override
    public void updateGenre(Genre genre) {
        genreDao.save(genre);
    }
}
