package homework1.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Класс CommunicationServiceImpl")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommunicationServiceImplTest {

    @Autowired
    private CommunicationService communicationService;

    /*@DisplayName("метод communicate не должен бросать ошибки")
    @Test
    void shouldNotThrowException() {
        Assertions.assertThatCode(() -> communicationService.communicate())
                .doesNotThrowAnyException();
    }*/
}
