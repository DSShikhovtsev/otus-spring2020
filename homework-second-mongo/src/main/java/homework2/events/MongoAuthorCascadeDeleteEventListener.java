package homework2.events;

import homework2.dao.BookDao;
import homework2.domain.Author;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteEventListener extends AbstractMongoEventListener<Author> {

    private final BookDao bookDao;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        bookDao.removeAuthorsArrayElementById(id);
    }
}
