package homework.gateway;

import homework.domain.Furniture;
import homework.domain.Tree;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Collection;

@MessagingGateway
public interface Factory {

    @Gateway(requestChannel = "treeChannel", replyChannel = "furnitureChannel")
    Collection<Furniture> process(Collection<Tree> typeTree);
}
