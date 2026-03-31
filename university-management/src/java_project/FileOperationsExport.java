package java_project;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileOperationsExport {

    // ------------------ Export Students ------------------
    public static void exportStudents(Path filePath, StudentManager studentManager) {
        try {
            List<String> lines = new ArrayList<>();
            // Header
            lines.add("id,regNo,fullName,email,dateOfBirth");

            // Student data
            studentManager.getStudents().forEach(s -> 
                lines.add(String.join(",",
                        String.valueOf(s.getId()),
                        s.getRegNo(),
                        s.getFullName(),
                        s.getEmail(),
                        s.getDateOfBirth().toString()))
            );

            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Students exported successfully to " + filePath.toString());
        } catch (IOException e) {
            System.out.println("Error exporting students: " + e.getMessage());
        }
    }

    // ------------------ Export Courses ------------------
    public static void exportCourses(Path filePath, CourseManager courseManager) {
        try {
            List<String> lines = new ArrayList<>();
            // Header
            lines.add("code,title,credits,instructor,semester,department");

            // Course data
            courseManager.getCourses().forEach(c -> 
                lines.add(String.join(",",
                        c.getCode(),
                        c.getTitle(),
                        String.valueOf(c.getCredits()),
                        c.getInstructor(),
                        c.getSemester().toString(),
                        c.getDepartment()))
            );

            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Courses exported successfully to " + filePath.toString());
        } catch (IOException e) {
            System.out.println("Error exporting courses: " + e.getMessage());
        }
    }

    // ------------------ Backup Exports ------------------
    public static void backupExports(Path exportDir) {
        try {
            if (!Files.exists(exportDir) || !Files.isDirectory(exportDir)) {
                System.out.println("Export directory does not exist: " + exportDir);
                return;
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path backupDir = exportDir.resolveSibling("backup_" + timestamp);

            Files.createDirectories(backupDir);

            try (Stream<Path> files = Files.list(exportDir)) {
                files.forEach(file -> {
                    try {
                        Path dest = backupDir.resolve(file.getFileName());
                        Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println("Failed to backup file: " + file.getFileName());
                    }
                });
            }

            System.out.println("Backup completed at: " + backupDir);
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }

    // ------------------ Recursive File Listing ------------------
    public static void listFilesRecursively(Path dir, int depth) {
        if (!Files.exists(dir) || !Files.isDirectory(dir)) return;

        try (Stream<Path> paths = Files.list(dir)) {
            paths.forEach(path -> {
                for (int i = 0; i < depth; i++) System.out.print("  ");
                System.out.print("|-- ");
                System.out.print(path.getFileName());
                if (Files.isDirectory(path)) System.out.println(" [DIR]");
                else System.out.println(" (" + path.toFile().length() + " bytes)");

                if (Files.isDirectory(path)) listFilesRecursively(path, depth + 1);
            });
        } catch (IOException e) {
            System.out.println("Error reading directory: " + e.getMessage());
        }
    }
}
