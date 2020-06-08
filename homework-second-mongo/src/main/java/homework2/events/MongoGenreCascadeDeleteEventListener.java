package homework2.events;

import homework2.dao.BookDao;
import homework2.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoGenreCascadeDeleteEventListener extends AbstractMongoEventListener<Genre> {

    private final BookDao bookDao;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        bookDao.removeGenresArrayElementById(id);
    }
}
