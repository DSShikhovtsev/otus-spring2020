package homework1.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Класс ConsoleServiceImpl")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConsoleOutputServiceTest {

    @Autowired
    private CommunicationOutputService communicationOutputService;

    /*@Test
    void methodHelloShouldNotThrowExceptions() {
        assertDoesNotThrow(communicationOutputService.bye("fio", 3));
    }*/
}