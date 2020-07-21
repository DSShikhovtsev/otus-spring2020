package batch.config.writers;

import batch.domain.mongo.Author;
import batch.service.mongo.AuthorServiceMongo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorItemWriter implements ItemWriter<Author> {

    private final AuthorServiceMongo service;

    public AuthorItemWriter(AuthorServiceMongo service) {
        this.service = service;
    }

    @Override
    public void write(List<? extends Author> list) throws Exception {
        list.forEach(service::save);
    }
}
