package homework2.service;

import homework2.dao.AuthorDao;
import homework2.domain.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceMongo implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceMongo(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Transactional
    @Override
    public void save(Author author) {
        authorDao.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorById(String id) {
        return authorDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Transactional
    @Override
    public void updateAuthor(Author author) {
        authorDao.save(author);
    }

    @Transactional
    @Override
    public void deleteAuthorById(String id) {
        authorDao.deleteById(id);
    }
}
