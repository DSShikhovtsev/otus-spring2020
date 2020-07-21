package batch.service.h2;


import batch.domain.h2.Author;

import java.util.List;

public interface AuthorService {

    void save(Author author);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthorById(Long id);
}
