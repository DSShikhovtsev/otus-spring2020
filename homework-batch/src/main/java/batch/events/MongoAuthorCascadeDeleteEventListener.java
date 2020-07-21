package batch.events;

import batch.reposinories.mongo.BookMongoRepository;
import batch.domain.mongo.Author;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteEventListener extends AbstractMongoEventListener<Author> {

    private final BookMongoRepository bookMongoRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        bookMongoRepository.removeAuthorsArrayElementById(id);
    }
}
