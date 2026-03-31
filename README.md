# University Management System

A Java command-line application to manage students, courses, enrollments, grades, and file operations for a university.

---

## Project Structure

```
university-management/
├── src/
│   └── java_project/         ← All .java source files go here
│       ├── MasterCLI.java    ← Main entry point
│       ├── Student.java
│       ├── StudentManager.java
│       ├── Course.java
│       ├── CourseManager.java
│       ├── Enrollment.java
│       ├── EnrollmentManager.java
│       ├── Semester.java
│       ├── Grade.java
│       ├── FileOperations.java
│       ├── FileOperationsExport.java
│       ├── BackupUtility.java
│       └── RecursiveUtility.java
├── data/
│   ├── sample_students.csv   ← Sample data for import testing
│   └── sample_courses.csv    ← Sample data for import testing
├── out/                      ← Compiled .class files (auto-generated)
├── .vscode/
│   ├── launch.json           ← Run config for VS Code
│   └── settings.json         ← Java source/output path config
└── .gitignore
```

---

## Requirements

- **Java JDK 17 or higher** (JDK 21 recommended)
- **VS Code** with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

---

## Setup & Running in VS Code

### Step 1 — Install the Java Extension Pack
Open VS Code → Extensions (Ctrl+Shift+X) → Search **"Extension Pack for Java"** → Install

### Step 2 — Open the project folder
```
File → Open Folder → select the university-management folder
```

### Step 3 — Run the program

**Option A: Using VS Code Run button**
- Open `src/java_project/MasterCLI.java`
- Click the ▶ **Run** button at the top right

**Option B: Using the terminal**
```bash
# From inside the university-management folder:

# Compile
javac -d out src/java_project/*.java

# Run
java -cp out java_project.MasterCLI
```

---

## Features

| Module | What it does |
|--------|-------------|
| **Student Management** | Add, list, update, deactivate students |
| **Course Management** | Add, list, update, deactivate, search courses by instructor/department/semester |
| **Enrollment & Grading** | Enroll/unenroll students, record marks, print transcripts |
| **File Operations** | Import/export students and courses via CSV, backup exports |

---

## Sample Data

Two sample CSV files are included in the `data/` folder for testing File Import features:

- `data/sample_students.csv` — 3 sample students
- `data/sample_courses.csv` — 4 sample courses

When prompted for a file path in the app, enter the full path to these files. Example:
```
data/sample_students.csv
```

---

## Grading Scale

| Grade | Marks Range | Grade Points |
|-------|------------|--------------|
| S     | 90–100     | 10           |
| A     | 80–89      | 9            |
| B     | 70–79      | 8            |
| C     | 60–69      | 7            |
| D     | 50–59      | 6            |
| E     | 40–49      | 5            |
| F     | Below 40   | 0            |

---

## Pushing to GitHub

```bash
cd university-management
git init
git add .
git commit -m "Initial commit: University Management System"
git branch -M main
git remote add origin https://github.com/sohamsharmadev-svg/java--24bai10638
git push -u origin main
```


---

## Common Errors & Fixes

| Error | Fix |
|-------|-----|
| `cannot find symbol: class Semester` | Make sure you compile ALL files together: `javac -d out src/java_project/*.java` |
| `Error: Could not find or load main class` | Use `java -cp out java_project.MasterCLI` (note the package prefix) |
| `javac: command not found` | Install JDK, not just JRE. Download from https://adoptium.net |
