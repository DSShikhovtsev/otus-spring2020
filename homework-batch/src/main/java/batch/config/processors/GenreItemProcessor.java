package batch.config.processors;

import batch.domain.mongo.Genre;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreItemProcessor implements ItemProcessor<batch.domain.h2.Genre, Genre> {

    @Override
    public Genre process(batch.domain.h2.Genre genre) throws Exception {
        return new Genre(genre.getId().toString(), genre.getDescription());
    }
}
