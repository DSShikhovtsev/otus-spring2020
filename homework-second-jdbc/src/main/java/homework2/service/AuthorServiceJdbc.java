package homework2.service;

import homework2.dao.AuthorDao;
import homework2.domain.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceJdbc implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceJdbc(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void putNewAuthor(Author author) {
        authorDao.insert(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Override
    public void updateAuthor(Author author) {
        authorDao.update(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorDao.deleteById(id);
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) {
        authorDao.addBookToAuthor(authorId, bookId);
    }

    @Override
    public void deleteBookFromAuthor(Long authorId, Long bookId) {
        authorDao.deleteBookFromAuthor(authorId, bookId);
    }
}
