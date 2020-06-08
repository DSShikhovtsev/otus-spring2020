package homework2.dao;

import homework2.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {

}
