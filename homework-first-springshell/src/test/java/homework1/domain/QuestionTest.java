package homework1.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Question")
public class QuestionTest {

    @DisplayName("должен корректно создаваться конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question("question", "answer");

        assertAll(
                () -> assertEquals("question", question.getQuestion()),
                () -> assertEquals("answer", question.getAnswer())
        );
    }
}
