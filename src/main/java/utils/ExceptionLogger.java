package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLogger {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);
    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
