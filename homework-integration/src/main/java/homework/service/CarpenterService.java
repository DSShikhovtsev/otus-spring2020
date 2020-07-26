package homework.service;

import homework.domain.Furniture;
import homework.domain.Tree;

public interface CarpenterService {

    Furniture convert(Tree tree) throws InterruptedException;
}
