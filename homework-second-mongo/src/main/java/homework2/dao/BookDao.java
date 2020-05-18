package homework2.dao;

import homework2.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends MongoRepository<Book, Long> {

    Book findById(String id);
    void deleteById(String id);
}
