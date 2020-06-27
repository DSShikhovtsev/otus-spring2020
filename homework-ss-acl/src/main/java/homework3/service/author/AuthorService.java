package homework3.service.author;

import homework3.domain.Author;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AuthorService {

    @PreAuthorize("hasPermission(#author, 'WRITE')")
    void save(Author author);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Author getAuthorById(Long id);

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Author> getAllAuthors();

    @PreAuthorize("hasPermission(#author, 'WRITE')")
    void updateAuthor(Author author);

    @PreAuthorize("hasPermission(#author, 'WRITE')")
    void deleteAuthor(Author author);
}
