package homework2.dao;

import homework2.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Long>, BookDaoCustom {

}
