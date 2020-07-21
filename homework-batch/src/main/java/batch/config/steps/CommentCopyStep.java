package batch.config.steps;

import batch.domain.h2.Comment;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class CommentCopyStep {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public CommentCopyStep(StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @StepScope
    @Bean
    public ItemStreamReader<Comment> commentItemReader() {
        return new JpaPagingItemReaderBuilder<Comment>()
                .entityManagerFactory(entityManagerFactory)
                .name("commentItemReader")
                .pageSize(5)
                .queryString("select c from Comment c")
                .build();
    }

    @Bean
    public Step commentCopyDataStep(ItemReader<Comment> reader,
                                   ItemProcessor<Comment, batch.domain.mongo.Comment> processor,
                                   ItemWriter<batch.domain.mongo.Comment> writer) {
        return stepBuilderFactory.get("commentCopyDataStep")
                .<Comment, batch.domain.mongo.Comment>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
