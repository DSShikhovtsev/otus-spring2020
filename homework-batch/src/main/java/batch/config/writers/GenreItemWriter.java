package batch.config.writers;

import batch.domain.mongo.Genre;
import batch.service.mongo.GenreServiceMongo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreItemWriter implements ItemWriter<Genre> {

    private final GenreServiceMongo service;

    public GenreItemWriter(GenreServiceMongo service) {
        this.service = service;
    }

    @Override
    public void write(List<? extends Genre> list) throws Exception {
        list.forEach(service::save);
    }
}
