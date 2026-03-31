package java_project;

import java.util.Scanner;

public class CourseManagementCLI {
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---- Course Management ----");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Deactivate Course");
            System.out.println("5. Search by Instructor");
            System.out.println("6. Search by Department");
            System.out.println("7. Search by Semester");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice) {
                case 1 -> {
                    System.out.print("Enter Course Code: "); String code = scanner.nextLine();
                    System.out.print("Enter Title: "); String title = scanner.nextLine();
                    System.out.print("Enter Credits: "); int credits = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter Instructor: "); String instructor = scanner.nextLine();
                    System.out.print("Enter Semester (SPRING, SUMMER, FALL): "); Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Enter Department: "); String department = scanner.nextLine();
                    manager.addCourse(new Course(code, title, credits, instructor, semester, department));
                }
                case 2 -> manager.listCourses();
                case 3 -> {
                    System.out.print("Enter Course Code to update: "); String code = scanner.nextLine();
                    System.out.print("Enter new Title: "); String title = scanner.nextLine();
                    System.out.print("Enter new Credits: "); int credits = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter new Instructor: "); String instructor = scanner.nextLine();
                    System.out.print("Enter new Semester (SPRING, SUMMER, FALL): "); Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Enter new Department: "); String department = scanner.nextLine();
                    manager.updateCourse(code, title, credits, instructor, semester, department);
                }
                case 4 -> {
                    System.out.print("Enter Course Code to deactivate: "); String code = scanner.nextLine();
                    manager.deactivateCourse(code);
                }
                case 5 -> {
                    System.out.print("Enter Instructor Name: "); String instructor = scanner.nextLine();
                    manager.searchByInstructor(instructor);
                }
                case 6 -> {
                    System.out.print("Enter Department: "); String dept = scanner.nextLine();
                    manager.searchByDepartment(dept);
                }
                case 7 -> {
                    System.out.print("Enter Semester (SPRING, SUMMER, FALL): "); Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
                    manager.searchBySemester(semester);
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }
}

