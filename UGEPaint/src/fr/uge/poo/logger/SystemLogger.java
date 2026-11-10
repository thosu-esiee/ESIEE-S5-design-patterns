package fr.uge.poo.logger;

public class SystemLogger implements Logger {
    private static SystemLogger instance;

    private SystemLogger() {}

    public static SystemLogger getInstance() {
        if (instance == null) {
            instance = new SystemLogger();
        }
        return instance;
    }

    public void log(Level level, String message) {
        System.err.println(level + " " + message);
    }
}