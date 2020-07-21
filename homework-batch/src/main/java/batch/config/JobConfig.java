package batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    public static final String COPY_JOB = "copyJob";

    private final JobBuilderFactory jobBuilderFactory;

    public JobConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job copyJob(Step authorCopyDataStep,
                       Step genreCopyDataStep,
                       Step bookCopyDataStep,
                       Step commentCopyDataStep) {
        return jobBuilderFactory.get("copyJob")
                .incrementer(new RunIdIncrementer())
                .start(authorCopyDataStep)
                .next(genreCopyDataStep)
                .next(bookCopyDataStep)
                .next(commentCopyDataStep)
                .build();
    }
}
