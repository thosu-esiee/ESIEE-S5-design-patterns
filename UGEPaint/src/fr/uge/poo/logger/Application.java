package fr.uge.poo.logger;

public class Application {
    static void main() {
        var logger = new FilterLogger(new SystemPathLogger(SystemLogger.getInstance(), new PathLogger("/tmp/logs.txt")), Level.WARNING);
        logger.log(Level.INFO, "Test info");
        logger.log(Level.WARNING, "Test warning");
        logger.log(Level.ERROR, "Test error");
    }
}
