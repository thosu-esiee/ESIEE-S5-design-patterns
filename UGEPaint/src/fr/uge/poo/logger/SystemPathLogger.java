package fr.uge.poo.logger;

public class SystemPathLogger implements Logger {

    private final SystemLogger systemLogger;
    private final PathLogger pathLogger;

    public SystemPathLogger(SystemLogger systemLogger, PathLogger pathLogger) {
        this.systemLogger = systemLogger;
        this.pathLogger = pathLogger;
    }

    @Override
    public void log(Level level, String message) {
        systemLogger.log(level, message);
        pathLogger.log(level, message);
    }
}
