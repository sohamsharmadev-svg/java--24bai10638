package java_project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class FileOperations {

    // Import students
    public static void importStudents(Path filePath, StudentManager studentManager) {
        try {
            Files.lines(filePath).skip(1).forEach(line -> {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String regNo = parts[1];
                String fullName = parts[2];
                String email = parts[3];
                LocalDate dob = LocalDate.parse(parts[4]);
                studentManager.addStudent(new Student(id, regNo, fullName, email, dob));
            });
            System.out.println("Students imported successfully!");
        } catch (IOException e) {
            System.out.println("Error reading students file: " + e.getMessage());
        }
    }

    // Import courses
    public static void importCourses(Path filePath, CourseManager courseManager) {
        try {
            Files.lines(filePath).skip(1).forEach(line -> {
                String[] parts = line.split(",");
                String code = parts[0];
                String title = parts[1];
                int credits = Integer.parseInt(parts[2]);
                String instructor = parts[3];
                Semester semester = Semester.valueOf(parts[4].toUpperCase());
                String department = parts[5];
                courseManager.addCourse(new Course(code, title, credits, instructor, semester, department));
            });
            System.out.println("Courses imported successfully!");
        } catch (IOException e) {
            System.out.println("Error reading courses file: " + e.getMessage());
        }
    }
}
