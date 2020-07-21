package batch.config.steps;

import batch.domain.h2.Author;
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
public class AuthorCopyStep {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public AuthorCopyStep(StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @StepScope
    @Bean
    public ItemStreamReader<Author> authorItemReader() {
        return new JpaPagingItemReaderBuilder<Author>()
                .entityManagerFactory(entityManagerFactory)
                .name("authorItemReader")
                .queryString("select a from Author a")
                .pageSize(5)
                .build();
    }

    @Bean
    public Step authorCopyDataStep(ItemReader<Author> reader,
                                   ItemProcessor<Author, batch.domain.mongo.Author> processor,
                                   ItemWriter<batch.domain.mongo.Author> writer) {
        return stepBuilderFactory.get("authorCopyDataStep")
                .<Author, batch.domain.mongo.Author>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
