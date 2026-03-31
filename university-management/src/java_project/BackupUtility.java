package java_project;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupUtility {

    public static void backupDirectory(Path sourceDir, Path backupRoot) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = backupRoot.resolve("backup_" + timestamp);
        try {
            Files.createDirectories(backupDir);
            Files.walk(sourceDir).forEach(source -> {
                try {
                    Path destination = backupDir.resolve(sourceDir.relativize(source));
                    if (Files.isDirectory(source)) {
                        Files.createDirectories(destination);
                    } else {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    System.out.println("Error copying: " + source + " -> " + e.getMessage());
                }
            });
            System.out.println("Backup completed at " + backupDir);
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }
}
