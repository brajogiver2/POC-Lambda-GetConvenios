package com.semillero.convenios.infrastructure;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logging adapter that writes structured log messages with ISO-8601 timestamps.
 * Wraps {@link java.util.logging.Logger} to provide consistent formatting.
 * In AWS Lambda, all java.util.logging output is automatically captured by CloudWatch Logs
 * under the /aws/lambda/&lt;function-name&gt; log group.
 */
public class LoggerAdapter {

    private final Logger logger;

    /**
     * Creates a logger for the given class.
     *
     * @param clazz The class to associate log entries with (shown in CloudWatch)
     */
    public LoggerAdapter(Class<?> clazz) {
        this.logger = Logger.getLogger(clazz.getName());
    }

    /**
     * Log an informational message with the current ISO-8601 timestamp.
     * Used for operational audit trail in CloudWatch.
     *
     * @param message Descriptive message about the operation being logged
     */
    public void info(String message) {
        logger.log(Level.INFO, "[{0}] {1}", new Object[]{Instant.now(), message});
    }

    /**
     * Log an error message with the current ISO-8601 timestamp and exception description.
     *
     * @param message   Context about the failure scenario
     * @param throwable The exception that caused the failure (its message is extracted)
     */
    public void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, "[{0}] {1} - {2}",
            new Object[]{Instant.now(), message, throwable.getMessage()});
    }
}
