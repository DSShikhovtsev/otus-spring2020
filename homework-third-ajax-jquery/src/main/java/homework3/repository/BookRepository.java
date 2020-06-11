package homework3.repository;

import homework3.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
}
