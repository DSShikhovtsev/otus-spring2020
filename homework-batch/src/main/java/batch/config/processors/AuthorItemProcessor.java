package batch.config.processors;

import batch.domain.mongo.Author;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorItemProcessor implements ItemProcessor<batch.domain.h2.Author, Author> {

    @Override
    public Author process(batch.domain.h2.Author author) throws Exception {
        return new Author(author.getId().toString(), author.getName());
    }
}
