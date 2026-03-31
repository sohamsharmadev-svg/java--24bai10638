package java_project;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class RecursiveUtility {

    // Print files recursively
    public static void listFiles(Path dir, int depth) {
        try (Stream<Path> paths = Files.walk(dir, depth)) {
            paths.forEach(p -> System.out.println(p));
        } catch (IOException e) {
            System.out.println("Error listing files: " + e.getMessage());
        }
    }

    // Compute total size recursively
    public static long computeTotalSize(Path dir) {
        try (Stream<Path> paths = Files.walk(dir)) {
            return paths.filter(Files::isRegularFile)
                        .mapToLong(p -> {
                            try { return Files.size(p); }
                            catch (IOException e) { return 0; }
                        })
                        .sum();
        } catch (IOException e) {
            System.out.println("Error computing size: " + e.getMessage());
            return 0;
        }
    }
}
