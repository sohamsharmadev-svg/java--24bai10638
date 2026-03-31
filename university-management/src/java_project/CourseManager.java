package java_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseManager {
    List<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }

    // Add a course
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    // List all courses
    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    // Update course by code
    public void updateCourse(String code, String title, int credits, String instructor, Semester semester, String department) {
        Optional<Course> optional = courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
        if (optional.isPresent()) {
            Course c = optional.get();
            Course updated = new Course(code, title, credits, instructor, semester, department);
            courses.set(courses.indexOf(c), updated);
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Course not found.");
        }
    }

    // Deactivate course by code
    public void deactivateCourse(String code) {
        Optional<Course> optional = courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
        if (optional.isPresent()) {
            optional.get().deactivate();
            System.out.println("Course deactivated successfully!");
        } else {
            System.out.println("Course not found.");
        }
    }

    // Search courses by instructor
    public void searchByInstructor(String instructor) {
        List<Course> result = courses.stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
        printList(result);
    }

    // Search courses by department
    public void searchByDepartment(String department) {
        List<Course> result = courses.stream()
                .filter(c -> c.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
        printList(result);
    }

    // Search courses by semester
    public void searchBySemester(Semester semester) {
        List<Course> result = courses.stream()
                .filter(c -> c.getSemester() == semester)
                .collect(Collectors.toList());
        printList(result);
    }

    private void printList(List<Course> list) {
        if (list.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course c : list) {
            System.out.println(c);
        }
    }
    public List<Course> getCourses() {
        return courses;
    }

}
