package homework2.service;

import homework2.dao.AuthorDao;
import homework2.domain.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceJdbc implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceJdbc(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Transactional
    @Override
    public void save(Author author) {
        authorDao.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorById(Long id) {
        return authorDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Transactional
    @Override
    public void updateAuthor(Author author) {
        authorDao.update(author);
    }

    @Transactional
    @Override
    public void deleteAuthorById(Long id) {
        authorDao.deleteById(id);
    }

    @Transactional
    @Override
    public void addBookByAuthorId(Long authorId, Long bookId) {
        authorDao.addBookByAuthorId(authorId, bookId);
    }

    @Transactional
    @Override
    public void deleteBookByAuthorId(Long authorId, Long bookId) {
        authorDao.deleteBookByAuthorId(authorId, bookId);
    }
}
