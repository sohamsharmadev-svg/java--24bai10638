package java_project;

public class Enrollment {
    private Student student;
    private Course course;
    private double marks;
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.marks = 0;
        this.grade = Grade.F;
    }

    // Getters and setters
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public double getMarks() { return marks; }
    public Grade getGrade() { return grade; }

    public void recordMarks(double marks) {
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    @Override
    public String toString() {
        return course.getCode() + " | " + course.getTitle() +
               " | Marks: " + marks +
               " | Grade: " + grade +
               " | Credits: " + course.getCredits();
    }
}
