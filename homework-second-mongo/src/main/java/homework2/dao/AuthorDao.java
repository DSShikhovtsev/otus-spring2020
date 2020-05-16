package homework2.dao;

import homework2.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorDao extends CrudRepository<Author, Long> {

    List<Author> findAll();
}
