package java_project;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

public class MasterCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Managers
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        EnrollmentManager enrollmentManager = new EnrollmentManager();

        int choice;
        do {
            System.out.println("=== University Management System ===");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment & Grading");
            System.out.println("4. File Operations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1 -> studentMenu(scanner, studentManager);
                case 2 -> courseMenu(scanner, courseManager);
                case 3 -> enrollmentMenu(scanner, studentManager, courseManager, enrollmentManager);
                case 4 -> fileMenu(scanner, studentManager, courseManager);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while(choice != 0);
    }
    

    private static void studentMenu(Scanner scanner, StudentManager studentManager) {
        int choice;
        do {
            System.out.println("--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1 -> {
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Reg No: "); String regNo = scanner.nextLine();
                    System.out.print("Full Name: "); String name = scanner.nextLine();
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("Date of Birth (yyyy-mm-dd): "); LocalDate dob = LocalDate.parse(scanner.nextLine());
                    studentManager.addStudent(new Student(id, regNo, name, email, dob));
                }
                case 2 -> studentManager.listStudents();
                case 3 -> {
                    System.out.print("Student ID to update: "); int idu = scanner.nextInt(); scanner.nextLine();
                    System.out.print("New Full Name: "); String name = scanner.nextLine();
                    System.out.print("New Email: "); String email = scanner.nextLine();
                    System.out.print("New DOB (yyyy-mm-dd): "); LocalDate dob = LocalDate.parse(scanner.nextLine());
                    studentManager.updateStudent(idu, name, email, dob);
                }
                case 4 -> {
                    System.out.print("Student ID to deactivate: "); int idd = scanner.nextInt(); scanner.nextLine();
                    studentManager.deactivateStudent(idd);
                }
                case 0 -> System.out.println("Back to main menu.");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }

    
    private static void courseMenu(Scanner scanner, CourseManager courseManager) {
        int choice;
        do {
            System.out.println("--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Deactivate Course");
            System.out.println("5. Search by Instructor");
            System.out.println("6. Search by Department");
            System.out.println("7. Search by Semester");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1 -> {
                    System.out.print("Code: "); String code = scanner.nextLine();
                    System.out.print("Title: "); String title = scanner.nextLine();
                    System.out.print("Credits: "); int credits = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Instructor: "); String instr = scanner.nextLine();
                    System.out.print("Semester (SPRING, SUMMER, FALL): "); Semester sem = Semester.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Department: "); String dept = scanner.nextLine();
                    courseManager.addCourse(new Course(code, title, credits, instr, sem, dept));
                }
                case 2 -> courseManager.listCourses();
                case 3 -> {
                    System.out.print("Course Code to update: "); String codeu = scanner.nextLine();
                    System.out.print("New Title: "); String title = scanner.nextLine();
                    System.out.print("New Credits: "); int credits = scanner.nextInt(); scanner.nextLine();
                    System.out.print("New Instructor: "); String instr = scanner.nextLine();
                    System.out.print("New Semester: "); Semester sem = Semester.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("New Department: "); String dept = scanner.nextLine();
                    courseManager.updateCourse(codeu, title, credits, instr, sem, dept);
                }
                case 4 -> {
                    System.out.print("Course Code to deactivate: "); String coded = scanner.nextLine();
                    courseManager.deactivateCourse(coded);
                }
                case 5 -> {
                    System.out.print("Instructor: "); String instr = scanner.nextLine();
                    courseManager.searchByInstructor(instr);
                }
                case 6 -> {
                    System.out.print("Department: "); String dept = scanner.nextLine();
                    courseManager.searchByDepartment(dept);
                }
                case 7 -> {
                    System.out.print("Semester: "); Semester sem = Semester.valueOf(scanner.nextLine().toUpperCase());
                    courseManager.searchBySemester(sem);
                }
                case 0 -> System.out.println("Back to main menu.");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }

    
    private static void enrollmentMenu(Scanner scanner, StudentManager sm, CourseManager cm, EnrollmentManager em) {
        int choice;
        do {
            System.out.println("--- Enrollment & Grading ---");
            System.out.println("1. Enroll Student");
            System.out.println("2. Unenroll Student");
            System.out.println("3. Record Marks");
            System.out.println("4. Print Transcript");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1 -> {
                    System.out.print("Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Course Code: "); String ccode = scanner.nextLine();
                    Student s = sm.getStudents().stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    Course c = cm.getCourses().stream().filter(co -> co.getCode().equalsIgnoreCase(ccode)).findFirst().orElse(null);
                    if(s != null && c != null) em.enrollStudent(s, c);
                    else System.out.println("Student or Course not found.");
                }
                case 2 -> {
                    System.out.print("Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Course Code: "); String ccode = scanner.nextLine();
                    Student s = sm.getStudents().stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    Course c = cm.getCourses().stream().filter(co -> co.getCode().equalsIgnoreCase(ccode)).findFirst().orElse(null);
                    if(s != null && c != null) em.unenrollStudent(s, c);
                    else System.out.println("Student or Course not found.");
                }
                case 3 -> {
                    System.out.print("Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Course Code: "); String ccode = scanner.nextLine();
                    System.out.print("Marks: "); double marks = scanner.nextDouble(); scanner.nextLine();
                    Student s = sm.getStudents().stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    Course c = cm.getCourses().stream().filter(co -> co.getCode().equalsIgnoreCase(ccode)).findFirst().orElse(null);
                    if(s != null && c != null) em.recordMarks(s, c, marks);
                    else System.out.println("Student or Course not found.");
                }
                case 4 -> {
                    System.out.print("Student ID: "); int sid = scanner.nextInt(); scanner.nextLine();
                    Student s = sm.getStudents().stream().filter(st -> st.getId()==sid).findFirst().orElse(null);
                    if(s != null) em.printTranscript(s);
                    else System.out.println("Student not found.");
                }
                case 0 -> System.out.println("Back to main menu.");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }

    
    
 // ---------------- File Menu ----------------
    private static void fileMenu(Scanner scanner, StudentManager studentManager, CourseManager courseManager) {
        int choice;
        do {
            System.out.println("\n--- File Operations ---");
            System.out.println("1. Import Students from CSV");
            System.out.println("2. Import Courses from CSV");
            System.out.println("3. Export Students to CSV");
            System.out.println("4. Export Courses to CSV");
            System.out.println("5. Backup Exported Files");
            System.out.println("6. List Files Recursively");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter path to students CSV: ");
                    String path = scanner.nextLine();
                    FileOperations.importStudents(Path.of(path), studentManager);
                }
                case 2 -> {
                    System.out.print("Enter path to courses CSV: ");
                    String path = scanner.nextLine();
                    FileOperations.importCourses(Path.of(path), courseManager);
                }
                case 3 -> {
                    System.out.print("Enter path to export students CSV: ");
                    String path = scanner.nextLine();
                    FileOperationsExport.exportStudents(Path.of(path), studentManager);
                }
                case 4 -> {
                    System.out.print("Enter path to export courses CSV: ");
                    String path = scanner.nextLine();
                    FileOperationsExport.exportCourses(Path.of(path), courseManager);
                }
                case 5 -> {
                    System.out.print("Enter export folder to backup: ");
                    String path = scanner.nextLine();
                    FileOperationsExport.backupExports(Path.of(path));
                }
                case 6 -> {
                    System.out.print("Enter folder to list recursively: ");
                    String path = scanner.nextLine();
                    FileOperationsExport.listFilesRecursively(Path.of(path), 0);
                }
                case 0 -> System.out.println("Back to main menu.");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }

}


