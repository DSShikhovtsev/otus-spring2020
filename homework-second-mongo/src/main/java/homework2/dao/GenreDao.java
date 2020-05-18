package homework2.dao;

import homework2.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreDao extends MongoRepository<Genre, Long> {

    Genre findById(String id);
    void deleteById(String id);
}
