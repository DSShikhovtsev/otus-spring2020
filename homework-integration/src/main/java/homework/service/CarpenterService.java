package homework.service;

import homework.domain.Furniture;
import homework.domain.Tree;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

@Service
public class CarpenterService {

    private static final Furniture[] FURNITURE = {new Furniture("table"), new Furniture("chair"), new Furniture("cupboard")};

    public Furniture convert(Tree tree) throws InterruptedException {
        System.out.println("-----------------------------");
        System.out.println("Converting tree to furniture");
        System.out.println("-----------------------------");
        Thread.sleep(5000L);
        System.out.println("-----------------------------");
        System.out.println("Done!");
        System.out.println("-----------------------------");
        return FURNITURE[RandomUtils.nextInt(0, FURNITURE.length)];
    }
}
