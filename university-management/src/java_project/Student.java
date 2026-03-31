package java_project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String regNo;
    private String fullName;
    private String email;
    private boolean isActive;
    private List<String> enrolledCourses;
    private LocalDate dateOfBirth;
    private LocalDateTime enrollmentDate;

    public Student(int id, String regNo, String fullName, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.regNo = regNo;
        this.fullName = fullName;
        this.email = email;
        this.isActive = true; // default active
        this.enrolledCourses = new ArrayList<>();
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = LocalDateTime.now();
    }

    // Getters
    public int getId() { return id; }
    public String getRegNo() { return regNo; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public boolean isActive() { return isActive; }
    public List<String> getEnrolledCourses() { return enrolledCourses; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }

    // Setters (for updating)
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    // Deactivate student
    public void deactivate() { isActive = false; }

    @Override
    public String toString() {
        return "Student ID: " + id +
               "\nReg No: " + regNo +
               "\nName: " + fullName +
               "\nEmail: " + email +
               "\nStatus: " + (isActive ? "Active" : "Inactive") +
               "\nDate of Birth: " + dateOfBirth +
               "\nEnrollment Date: " + enrollmentDate +
               "\nEnrolled Courses: " + enrolledCourses + "\n";
    }
}
