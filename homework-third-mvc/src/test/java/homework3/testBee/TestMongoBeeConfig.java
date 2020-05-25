package homework3.testBee;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import homework3.testBee.changelog.TestDBChangelog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class TestMongoBeeConfig {

    private final MongoClient mongo;
    private final MongoTemplate template;

    public TestMongoBeeConfig(MongoClient mongo, MongoTemplate template) {
        this.mongo = mongo;
        this.template = template;
    }

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName("test");
        runner.setChangeLogsScanPackage(TestDBChangelog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        runner.setMongoTemplate(template);
        return runner;
    }
}
