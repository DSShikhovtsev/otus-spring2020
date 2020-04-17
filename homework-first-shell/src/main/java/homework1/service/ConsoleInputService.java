package homework1.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@NoArgsConstructor
public class ConsoleInputService implements CommunicationInputService {

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
