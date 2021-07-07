package hystrix.service.author;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import hystrix.domain.Author;
import hystrix.repository.AuthorRepository;
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

    @HystrixCommand(commandKey = "getAuthor", fallbackMethod = "returnDefault", commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1")
    })
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

    private Author returnDefault(String s) {
        return new Author("---", "+++");
    }
}
