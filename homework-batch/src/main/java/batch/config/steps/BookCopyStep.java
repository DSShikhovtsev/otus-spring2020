package batch.config.steps;

import batch.domain.h2.Book;
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
public class BookCopyStep {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public BookCopyStep(StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @StepScope
    @Bean
    public ItemStreamReader<Book> bookItemReader() {
        return new JpaPagingItemReaderBuilder<Book>()
                .entityManagerFactory(entityManagerFactory)
                .name("bookItemReader")
                .pageSize(5)
                .queryString("select b from Book b")
                .build();
    }

    @Bean
    public Step bookCopyDataStep(ItemReader<Book> reader,
                                   ItemProcessor<Book, batch.domain.mongo.Book> processor,
                                   ItemWriter<batch.domain.mongo.Book> writer) {
        return stepBuilderFactory.get("bookCopyDataStep")
                .<Book, batch.domain.mongo.Book>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
