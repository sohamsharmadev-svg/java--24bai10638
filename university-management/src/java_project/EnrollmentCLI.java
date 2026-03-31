package java_project;

import java.util.Scanner;

public class EnrollmentCLI {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        EnrollmentManager enrollmentManager = new EnrollmentManager();
        Scanner scanner = new Scanner(System.in);

        // Sample students & courses
        Student s1 = new Student(1, "R001", "Alice", "alice@example.com", java.time.LocalDate.of(2000,1,1));
        Student s2 = new Student(2, "R002", "Bob", "bob@example.com", java.time.LocalDate.of(2001,2,2));
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);

        Course c1 = new Course("CSE101", "Data Structures", 4, "Dr. Smith", Semester.SPRING, "CSE");
        Course c2 = new Course("MAT101", "Calculus", 3, "Dr. John", Semester.SPRING, "Math");
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);

        int choice;
        do {
            System.out.println("---- Enrollment & Grading ----");
            System.out.println("1. Enroll Student");
            System.out.println("2. Unenroll Student");
            System.out.println("3. Record Marks");
            System.out.println("4. Print Transcript");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1 -> {
                    System.out.print("Enter Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter Course Code: "); String ccode = scanner.nextLine();
                    Student s = studentManager.getStudents().stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    Course c = courseManager.courses.stream().filter(co -> co.getCode().equalsIgnoreCase(ccode)).findFirst().orElse(null);
                    if (s != null && c != null) enrollmentManager.enrollStudent(s, c);
                    else System.out.println("Student or Course not found.");
                }
                case 2 -> {
                    System.out.print("Enter Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter Course Code: "); String ccode = scanner.nextLine();
                    Student s = studentManager.students.stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    Course c = courseManager.courses.stream().filter(co -> co.getCode().equalsIgnoreCase(ccode)).findFirst().orElse(null);
                    if (s != null && c != null) enrollmentManager.unenrollStudent(s, c);
                    else System.out.println("Student or Course not found.");
                }
                case 3 -> {
                    System.out.print("Enter Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter Course Code: "); String ccode = scanner.nextLine();
                    System.out.print("Enter Marks: "); double marks = scanner.nextDouble(); scanner.nextLine();
                    Student s = studentManager.students.stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    Course c = courseManager.courses.stream().filter(co -> co.getCode().equalsIgnoreCase(ccode)).findFirst().orElse(null);
                    if (s != null && c != null) enrollmentManager.recordMarks(s, c, marks);
                    else System.out.println("Student or Course not found.");
                }
                case 4 -> {
                    System.out.print("Enter Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    Student s = studentManager.students.stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    if (s != null) enrollmentManager.printTranscript(s);
                    else System.out.println("Student not found.");
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }
}
