package batch.service.mongo;

import batch.reposinories.mongo.AuthorRepository;
import batch.domain.mongo.Author;
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
        return authorRepository.findById(id);
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
