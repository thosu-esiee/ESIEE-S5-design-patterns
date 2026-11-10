package fr.uge.poo.stp.q1;


import java.util.Scanner;

public class Triviale {
    static void main() {
        var visitor = new TrivialeVisitor();
        var scanner = new Scanner(System.in);
        for(;;) {
            var line = scanner.nextLine();
            var command = STPParser.parse(line);
            if (command.isEmpty()) {
                System.out.println("Pas compris");
            } else {
                command.orElseThrow().accept(visitor);
            }
        }
    }
}
