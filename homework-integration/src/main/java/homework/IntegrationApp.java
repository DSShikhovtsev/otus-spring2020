package homework;

import homework.service.WorkService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@Configuration
@EnableScheduling
@EnableIntegration
@IntegrationComponentScan
public class IntegrationApp {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(IntegrationApp.class);

        WorkService workService = ctx.getBean(WorkService.class);

        workService.treeWork();
    }
}
