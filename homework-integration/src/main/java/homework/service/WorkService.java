package homework.service;

import homework.domain.Furniture;
import homework.domain.Tree;
import homework.Factory;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {

    private static final String[] WOOD = {"pine", "linden", "birch"};
    private final Factory factory;

    public WorkService(Factory factory) {
        this.factory = factory;
    }

    public void treeWork() throws InterruptedException {
        System.out.println("Start creation!");
        Collection<Furniture> furniture = factory.process(generateOrderItems());
        System.out.println("End of creation: " + furniture.stream().map(Furniture::getType).collect(Collectors.joining(" | ")));
        System.out.println("\n\n\n");
        Thread.sleep(5000);
    }

    @Bean
    public IntegrationFlow factoryFlow() {
        return IntegrationFlows.from("treeChannel")
                .split()
                .handle("carpenterService", "convert")
                .aggregate()
                .channel("furnitureChannel")
                .get();
    }

    private static Tree generateOrderItem() {
        return new Tree(WOOD[RandomUtils.nextInt(0, WOOD.length)]);
    }

    private static Collection<Tree> generateOrderItems() {
        List<Tree> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            items.add(generateOrderItem());
        }
        return items;
    }
}
