package homework1.runner;

import homework1.service.CommunicationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    private final CommunicationService communicationService;

    public CommandRunner(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public void run(String... args) throws Exception {
        communicationService.communicate();
    }
}
