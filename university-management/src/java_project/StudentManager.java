package java_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentManager {
    List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // List all students
    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Update student info by ID
    public void updateStudent(int id, String fullName, String email, LocalDate dateOfBirth) {
        Optional<Student> optional = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (optional.isPresent()) {
            Student s = optional.get();
            s = new Student(s.getId(), s.getRegNo(), fullName, email, dateOfBirth); // simple update
            students.set(students.indexOf(optional.get()), s);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Deactivate student by ID
    public void deactivateStudent(int id) {
        Optional<Student> optional = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (optional.isPresent()) {
            optional.get().deactivate();
            System.out.println("Student deactivated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }
 // Inside StudentManager class
    public List<Student> getStudents() {
        return students;
    }

}

