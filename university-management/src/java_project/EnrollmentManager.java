package java_project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentManager {
    private List<Enrollment> enrollments;

    public EnrollmentManager() {
        enrollments = new ArrayList<>();
    }

    // Enroll student
    public void enrollStudent(Student student, Course course) {
        // Check if already enrolled
        boolean already = enrollments.stream()
                .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        if (already) {
            System.out.println("Student already enrolled in this course.");
            return;
        }

        // Business rule: max 18 credits per semester
        int totalCredits = enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();
        if (totalCredits + course.getCredits() > 18) {
            System.out.println("Cannot enroll: exceeds maximum 18 credits per semester.");
            return;
        }

        enrollments.add(new Enrollment(student, course));
        student.getEnrolledCourses().add(course.getCode());
        System.out.println("Student enrolled successfully!");
    }

    // Unenroll student
    public void unenrollStudent(Student student, Course course) {
        Enrollment toRemove = enrollments.stream()
                .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
                .findFirst()
                .orElse(null);
        if (toRemove != null) {
            enrollments.remove(toRemove);
            student.getEnrolledCourses().remove(course.getCode());
            System.out.println("Student unenrolled successfully!");
        } else {
            System.out.println("Enrollment not found.");
        }
    }

    // Record marks
    public void recordMarks(Student student, Course course, double marks) {
        Enrollment e = enrollments.stream()
                .filter(en -> en.getStudent().equals(student) && en.getCourse().equals(course))
                .findFirst()
                .orElse(null);
        if (e != null) {
            e.recordMarks(marks);
            System.out.println("Marks recorded: Grade = " + e.getGrade());
        } else {
            System.out.println("Enrollment not found.");
        }
    }

    // Compute GPA
    public double computeGPA(Student student) {
        List<Enrollment> studentEnrollments = enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());

        double totalPoints = studentEnrollments.stream()
                .mapToDouble(e -> e.getGrade().getPoints() * e.getCourse().getCredits())
                .sum();
        int totalCredits = studentEnrollments.stream()
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();

        if (totalCredits == 0) return 0;
        return totalPoints / totalCredits;
    }

    // Print transcript
    public void printTranscript(Student student) {
        System.out.println("---- Transcript for " + student.getFullName() + " ----");
        List<Enrollment> studentEnrollments = enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
        if (studentEnrollments.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Enrollment e : studentEnrollments) {
                System.out.println(e);
            }
            System.out.printf("GPA: %.2f%n", computeGPA(student));
        }
        System.out.println("-------------------------------");
    }
}

