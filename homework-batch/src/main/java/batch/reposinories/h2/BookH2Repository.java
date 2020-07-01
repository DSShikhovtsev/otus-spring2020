package batch.reposinories.h2;

import batch.domain.h2.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookH2Repository extends JpaRepository<Book, Long>, BookH2RepositoryCustom {

}
