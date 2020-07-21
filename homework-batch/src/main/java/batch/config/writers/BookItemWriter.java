package batch.config.writers;

import batch.domain.mongo.Book;
import batch.service.mongo.BookServiceMongo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookItemWriter implements ItemWriter<Book> {

    private final BookServiceMongo service;

    public BookItemWriter(BookServiceMongo service) {
        this.service = service;
    }

    @Override
    public void write(List<? extends Book> list) throws Exception {
        list.forEach(service::save);
    }
}
