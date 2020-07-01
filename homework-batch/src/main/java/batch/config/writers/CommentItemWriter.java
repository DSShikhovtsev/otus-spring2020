package batch.config.writers;

import batch.domain.mongo.Comment;
import batch.service.mongo.CommentServiceMongo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentItemWriter implements ItemWriter<Comment> {

    private final CommentServiceMongo service;

    public CommentItemWriter(CommentServiceMongo service) {
        this.service = service;
    }

    @Override
    public void write(List<? extends Comment> list) throws Exception {
        list.forEach(service::save);
    }
}
