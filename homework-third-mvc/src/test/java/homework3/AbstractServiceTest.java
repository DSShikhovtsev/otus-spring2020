package homework3;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@ComponentScan({"homework3.service", "homework3.repository", "homework3.testBee"})
public abstract class AbstractServiceTest {
}
