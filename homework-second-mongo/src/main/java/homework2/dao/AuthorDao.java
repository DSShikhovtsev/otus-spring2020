package homework2.dao;

import homework2.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorDao extends MongoRepository<Author, Long> {

    Author findById(String id);
    void deleteById(String id);
}
