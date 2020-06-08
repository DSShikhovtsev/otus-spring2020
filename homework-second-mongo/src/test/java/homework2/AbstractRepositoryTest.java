package homework2;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@ComponentScan({"homework2.service", "homework2.dao", "homework2.testBee"})
public abstract class AbstractRepositoryTest {
}
