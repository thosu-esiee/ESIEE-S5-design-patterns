package fr.uge.poo.stp;

import com.evilcorp.stp.HelloCmd;
import com.evilcorp.stp.STPParser;

import java.util.Scanner;

public class Triviale {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            var line = scanner.nextLine();
            var command = STPParser.parse(line);
            if (command.isEmpty()) {
                System.out.println("Pas compris");
            } else {
                if (command.orElseThrow() instanceof HelloCmd) {
                    System.out.println("Au revoir");
                } else {
                    System.out.println("non implemente");
                }
            }
        }
    }
}
