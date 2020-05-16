package homework2.dao;

import homework2.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, Long>, BookRepositoryCustom {

    List<Book> findAll();
}
