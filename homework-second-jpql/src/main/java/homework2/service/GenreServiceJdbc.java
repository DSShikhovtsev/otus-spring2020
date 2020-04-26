package homework2.service;

import homework2.dao.GenreDao;
import homework2.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceJdbc implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceJdbc(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void save(Genre genre) {
        genreDao.save(genre);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public void deleteGenreById(Long id) {
        genreDao.deleteById(id);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreDao.update(genre);
    }
}
