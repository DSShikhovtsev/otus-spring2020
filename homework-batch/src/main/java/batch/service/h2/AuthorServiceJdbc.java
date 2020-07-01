package batch.service.h2;

import batch.domain.h2.Author;
import batch.reposinories.h2.AuthorH2Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceJdbc implements AuthorService {

    private final AuthorH2Repository repository;

    public AuthorServiceJdbc(AuthorH2Repository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void updateAuthor(Author author) {
        repository.save(author);
    }

    @Transactional
    @Override
    public void deleteAuthorById(Long id) {
        repository.deleteById(id);
    }
}
