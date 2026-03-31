package java_project;

import java.time.LocalDate;
import java.util.Scanner;

public class StudentManagementCLI {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---- Student Management ----");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice) {
                case 1 -> {
                    System.out.print("Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter RegNo: "); String regNo = scanner.nextLine();
                    System.out.print("Enter Full Name: "); String name = scanner.nextLine();
                    System.out.print("Enter Email: "); String email = scanner.nextLine();
                    System.out.print("Enter Date of Birth (YYYY-MM-DD): "); String dob = scanner.nextLine();
                    manager.addStudent(new Student(id, regNo, name, email, LocalDate.parse(dob)));
                }
                case 2 -> manager.listStudents();
                case 3 -> {
                    System.out.print("Enter ID to update: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Enter new Full Name: "); String name = scanner.nextLine();
                    System.out.print("Enter new Email: "); String email = scanner.nextLine();
                    System.out.print("Enter new Date of Birth (YYYY-MM-DD): "); String dob = scanner.nextLine();
                    manager.updateStudent(id, name, email, LocalDate.parse(dob));
                }
                case 4 -> {
                    System.out.print("Enter ID to deactivate: "); int id = scanner.nextInt(); scanner.nextLine();
                    manager.deactivateStudent(id);
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }
}

