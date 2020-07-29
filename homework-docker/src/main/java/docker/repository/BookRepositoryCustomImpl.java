package docker.repository;

import docker.domain.Book;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void removeAuthorsArrayElementById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("authors", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void removeGenresArrayElementById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("genres", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
