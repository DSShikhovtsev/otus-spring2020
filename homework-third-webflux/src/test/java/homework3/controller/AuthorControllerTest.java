package homework3.controller;

import homework3.domain.Author;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

@DisplayName("Controller для работы с авторами")
@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorControllerTest  {

    @Autowired
    @Qualifier("authorRouter")
    private RouterFunction router;

    @Test
    public void testRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(router)
                .build();

        client.get()
                .uri("/flux/showAuthors")
                .exchange()
                .expectStatus()
                .isOk();
    }
}