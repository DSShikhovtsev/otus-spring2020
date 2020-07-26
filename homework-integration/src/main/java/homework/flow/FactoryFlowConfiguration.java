package homework.flow;

import homework.service.CarpenterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class FactoryFlowConfiguration {

    @Bean
    public IntegrationFlow factoryFlow() {
        return IntegrationFlows.from("treeChannel")
                .split()
                .<CarpenterService>handle("carpenterServiceImpl", "convert")
                .aggregate()
                .channel("furnitureChannel")
                .get();
    }
}
