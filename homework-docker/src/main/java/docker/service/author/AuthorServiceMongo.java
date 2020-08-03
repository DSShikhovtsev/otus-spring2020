package docker.service.author;

import docker.repository.AuthorRepository;
import docker.domain.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceMongo implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceMongo(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorById(String id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void deleteAuthorById(String id) {
        authorRepository.deleteById(id);
    }
}
