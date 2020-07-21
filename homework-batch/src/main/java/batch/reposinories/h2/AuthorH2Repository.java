package batch.reposinories.h2;

import batch.domain.h2.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorH2Repository extends JpaRepository<Author, Long> {

}
