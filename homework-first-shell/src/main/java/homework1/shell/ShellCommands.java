package homework1.shell;

import homework1.service.CommunicationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommands {

    private final CommunicationService communicationService;
    private static boolean auth = false;

    @Autowired
    public ShellCommands(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @ShellMethod(value = "Start command", key = {"s", "start"})
    @ShellMethodAvailability(value = "getAuth")
    public void startTest() {
        communicationService.communicate();
        auth = false;
    }

    @ShellMethod(value = "Login command", key = {"l", "log", "login"})
    public void authenticate(@ShellOption(defaultValue = "name") String name) {
        auth = true;
    }

    public Availability getAuth() {
        return auth ? Availability.available() : Availability.unavailable("Сначала залогиньтесь!");
    }
}
