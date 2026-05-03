package Utils;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils - File operation utilities
 */
public class FileUtils {

    /**
     * Read file content as string
     * @param filePath Path to file
     * @return File content
     */
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Read file line by line
     * @param filePath Path to file
     * @return List of lines
     */
    public static List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    /**
     * Write string to file
     * @param filePath Path to file
     * @param content Content to write
     */
    public static void writeToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
    }

    /**
     * Append string to file
     * @param filePath Path to file
     * @param content Content to append
     */
    public static void appendToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }

    /**
     * Check if file exists
     * @param filePath Path to file
     * @return true if exists
     */
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    /**
     * Delete file
     * @param filePath Path to file
     * @return true if deleted
     */
    public static boolean deleteFile(String filePath) throws IOException {
        return Files.deleteIfExists(Paths.get(filePath));
    }

    /**
     * Get file size in bytes
     * @param filePath Path to file
     * @return File size
     */
    public static long getFileSize(String filePath) throws IOException {
        return Files.size(Paths.get(filePath));
    }

    /**
     * Get file extension
     * @param fileName File name
     * @return Extension (without dot)
     */
    public static String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0) {
            return fileName.substring(lastDot + 1);
        }
        return "";
    }

    /**
     * Create directory if not exists
     * @param dirPath Directory path
     */
    public static void createDirectory(String dirPath) throws IOException {
        Files.createDirectories(Paths.get(dirPath));
    }

    /**
     * List files in directory
     * @param dirPath Directory path
     * @return List of file names
     */
    public static List<String> listFiles(String dirPath) throws IOException {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dirPath))) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    fileNames.add(path.getFileName().toString());
                }
            }
        }
        return fileNames;
    }

    /**
     * Copy file
     * @param source Source path
     * @param destination Destination path
     */
    public static void copyFile(String source, String destination) throws IOException {
        Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Move file
     * @param source Source path
     * @param destination Destination path
     */
    public static void moveFile(String source, String destination) throws IOException {
        Files.move(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Get human-readable file size
     * @param bytes Size in bytes
     * @return Human readable string
     */
    public static String getHumanReadableSize(long bytes) {
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double size = bytes;

        while (size >= 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", size, units[unitIndex]);
    }
}