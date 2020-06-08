package homework2.dao;

import homework2.domain.Book;
import homework2.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment, Long> {

    Comment findById(String id);
    void deleteById(String id);
    void deleteByBook(Book book);
}
