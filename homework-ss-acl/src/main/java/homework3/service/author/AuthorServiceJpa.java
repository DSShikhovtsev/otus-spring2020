package homework3.service.author;

import homework3.domain.Author;
import homework3.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceJpa implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceJpa(AuthorRepository repository) {
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
    public void deleteAuthor(Author author) {
        repository.deleteById(author.getId());
    }
}
