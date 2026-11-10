package fr.uge.poo.stp.q1;
import com.evilcorp.stp.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    static void main(String[] args) {
        var visitor = new ApplicationVisitor();
        var scan = new Scanner(System.in);
        while (scan.hasNextLine()){
            var line = scan.nextLine();
            if (line.equals("quit")){
                break;
            }
            Optional<STPCommand> answer = STPParser.parse(line);
            if (!answer.isPresent()){
                System.out.println("Unrecognized command");
                continue;
            }
            var command = answer.get();
            command.accept(visitor);
        }
    }
}
