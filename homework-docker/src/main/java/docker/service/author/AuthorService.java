package docker.service.author;

import docker.domain.Author;

import java.util.List;

public interface AuthorService {

    void save(Author author);
    Author getAuthorById(String id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthorById(String id);
}