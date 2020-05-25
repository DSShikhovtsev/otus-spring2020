package homework3;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@WebMvcTest
@EnableMongoRepositories
@ComponentScan({"homework3.repository", "homework3.testBee"})
public abstract class AbstractControllerTest {
}
