package webflux.bee;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import webflux.bee.changelog.DatabaseChangelog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoBeeConfig {

    private final MongoClient mongo;
    private final MongoTemplate template;

    public MongoBeeConfig(MongoClient mongo, MongoTemplate template) {
        this.mongo = mongo;
        this.template = template;
    }

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName("library");
        runner.setChangeLogsScanPackage(DatabaseChangelog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        runner.setMongoTemplate(template);
        return runner;
    }
}
