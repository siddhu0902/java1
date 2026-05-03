package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * TimeUtils - Time-related utility functions
 */
public class TimeUtils {

    private static final DateTimeFormatter STANDARD_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter FILE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private static final DateTimeFormatter LOG_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Get current timestamp as string
     * @return Current timestamp
     */
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(STANDARD_FORMATTER);
    }

    /**
     * Get current timestamp for file names
     * @return File-friendly timestamp
     */
    public static String getFileTimestamp() {
        return LocalDateTime.now().format(FILE_FORMATTER);
    }

    /**
     * Get current timestamp for logs
     * @return Log-friendly timestamp
     */
    public static String getLogTimestamp() {
        return LocalDateTime.now().format(LOG_FORMATTER);
    }

    /**
     * Format duration in milliseconds to human readable
     * @param milliseconds Duration in milliseconds
     * @return Human readable string
     */
    public static String formatDuration(long milliseconds) {
        if (milliseconds < 0) return "0 ms";

        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        milliseconds %= 1000;
        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        StringBuilder result = new StringBuilder();

        if (days > 0) result.append(days).append("d ");
        if (hours > 0) result.append(hours).append("h ");
        if (minutes > 0) result.append(minutes).append("m ");
        if (seconds > 0) result.append(seconds).append("s ");
        if (milliseconds > 0 && days == 0 && hours == 0 && minutes == 0) {
            result.append(milliseconds).append("ms");
        }

        return result.toString().trim();
    }

    /**
     * Sleep for specified milliseconds (with try-catch)
     * @param milliseconds Time to sleep
     * @return true if slept completely, false if interrupted
     */
    public static boolean sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * Calculate time difference between two timestamps
     * @param start Start time
     * @param end End time
     * @return Difference in milliseconds
     */
    public static long timeDifference(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MILLIS.between(start, end);
    }

    /**
     * Parse timestamp string to LocalDateTime
     * @param timestamp Timestamp string
     * @return LocalDateTime object
     */
    public static LocalDateTime parseTimestamp(String timestamp) {
        try {
            return LocalDateTime.parse(timestamp, STANDARD_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Add delay with visual indicator (for demonstrations)
     * @param milliseconds Delay in milliseconds
     * @param message Message to display during delay
     */
    public static void delayWithIndicator(long milliseconds, String message) {
        System.out.print(message);
        long start = System.currentTimeMillis();
        long elapsed = 0;

        while (elapsed < milliseconds) {
            System.out.print(".");
            try {
                Thread.sleep(milliseconds / 10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            elapsed = System.currentTimeMillis() - start;
        }
        System.out.println(" Done!");
    }

    /**
     * Get time of day as string (Morning, Afternoon, Evening, Night)
     * @return Time of day
     */
    public static String getTimeOfDay() {
        int hour = LocalDateTime.now().getHour();

        if (hour >= 5 && hour < 12) return "Morning";
        if (hour >= 12 && hour < 17) return "Afternoon";
        if (hour >= 17 && hour < 21) return "Evening";
        return "Night";
    }

    /**
     * Check if current time is within business hours (9 AM - 5 PM)
     * @return true if business hours
     */
    public static boolean isBusinessHours() {
        int hour = LocalDateTime.now().getHour();
        return hour >= 9 && hour < 17;
    }

    /**
     * Calculate estimated completion time
     * @param startTime Start time
     * @param progress Progress (0.0 to 1.0)
     * @param estimatedTotal Estimated total time in seconds
     * @return Estimated remaining time string
     */
    public static String estimateRemainingTime(LocalDateTime startTime, double progress, long estimatedTotalSeconds) {
        if (progress <= 0) return "Calculating...";

        long elapsedSeconds = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
        long remainingSeconds = (long)((estimatedTotalSeconds / progress) - elapsedSeconds);

        if (remainingSeconds < 0) remainingSeconds = 0;

        return formatDuration(remainingSeconds * 1000);
    }
}