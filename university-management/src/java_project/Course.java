package java_project;
public class Course {
    private String code;
    private String title;
    private int credits;
    private String instructor;
    private Semester semester;
    private String department;
    private boolean isActive;

    public Course(String code, String title, int credits, String instructor, Semester semester, String department) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.semester = semester;
        this.department = department;
        this.isActive = true;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return isActive; }
    public void deactivate() { isActive = false; }

    @Override
    public String toString() {
        return "Course Code: " + code +
               "\nTitle: " + title +
               "\nCredits: " + credits +
               "\nInstructor: " + instructor +
               "\nSemester: " + semester +
               "\nDepartment: " + department +
               "\nStatus: " + (isActive ? "Active" : "Inactive") + "\n";
    }
}
