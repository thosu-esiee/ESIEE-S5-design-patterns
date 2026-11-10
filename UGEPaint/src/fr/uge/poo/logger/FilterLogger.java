package fr.uge.poo.logger;

public class FilterLogger implements Logger {

    private final Logger logger;
    private final Level level;

    public FilterLogger(Logger logger, Level level) {
        this.logger = logger;
        this.level = level;
    }

    @Override
    public void log(Level level, String message) {
        if (this.level.compareTo(level) >= 0) {
            logger.log(level, message);
        }
    }

    static void main() {
        var logger = new FilterLogger(SystemLogger.getInstance(), Level.WARNING);
        logger.log(Level.INFO, "test info");
        logger.log(Level.WARNING, "test warning");
        logger.log(Level.ERROR, "test error");
    }
}
