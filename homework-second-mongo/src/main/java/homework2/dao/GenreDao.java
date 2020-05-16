package homework2.dao;

import homework2.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreDao extends CrudRepository<Genre, Long> {

    List<Genre> findAll();
}
