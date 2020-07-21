package batch.reposinories.h2;

import batch.domain.h2.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreH2Repository extends JpaRepository<Genre, Long> {

}
