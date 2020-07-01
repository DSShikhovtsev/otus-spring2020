package batch.config.steps;

import batch.domain.h2.Genre;
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
public class GenreCopyStep {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public GenreCopyStep(StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @StepScope
    @Bean
    public ItemStreamReader<Genre> genreItemReader() {
        return new JpaPagingItemReaderBuilder<Genre>()
                .entityManagerFactory(entityManagerFactory)
                .name("genreItemReader")
                .pageSize(5)
                .queryString("select g from Genre g")
                .build();
    }

    @Bean
    public Step genreCopyDataStep(ItemReader<Genre> reader,
                                   ItemProcessor<Genre, batch.domain.mongo.Genre> processor,
                                   ItemWriter<batch.domain.mongo.Genre> writer) {
        return stepBuilderFactory.get("genreCopyDataStep")
                .<Genre, batch.domain.mongo.Genre>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
