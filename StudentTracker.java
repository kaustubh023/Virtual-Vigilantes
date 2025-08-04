import java.util.*;

class Course {
    String name;
    double grade;

    Course(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

class Student {
    String name;
    List<Course> courses = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }

    void addCourse(String courseName, double grade) {
        courses.add(new Course(courseName, grade));
    }

    void updateGrade(String courseName, double newGrade) {
        for (Course course : courses) {
            if (course.name.equalsIgnoreCase(courseName)) {
                course.grade = newGrade;
                System.out.println("Grade updated successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    void showGrades() {
        System.out.println("\nGrades for student: " + name);
        if (courses.isEmpty()) {
            System.out.println("No courses added yet.");
            return;
        }

        double total = 0;
        for (Course course : courses) {
            System.out.println(course.name + ": " + course.grade);
            total += course.grade;
        }

        double average = total / courses.size();
        System.out.println("Average grade: " + average);
    }
}

public class StudentjTracker {
    static Scanner scanner = new Scanner(System.in);
    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Student Grade Tracker");

        while (true) {
            showMenu();
            int choice = getNumber("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourseToStudent();
                    break;
                case 3:
                    updateStudentGrade();
                    break;
                case 4:
                    showAllGrades();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course and Grade");
        System.out.println("3. Update Grade");
        System.out.println("4. Show All Grades");
        System.out.println("5. Exit");
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        studentList.add(new Student(name));
        System.out.println("Student added successfully.");
    }

    static void addCourseToStudent() {
        Student student = findStudent();
        if (student == null) return;

        System.out.print("Enter course name: ");
        String course = scanner.nextLine();
        double grade = getDecimal("Enter grade: ");
        student.addCourse(course, grade);
        System.out.println("Course and grade added successfully.");
    }

    static void updateStudentGrade() {
        Student student = findStudent();
        if (student == null) return;

        System.out.print("Enter course name to update: ");
        String course = scanner.nextLine();
        double newGrade = getDecimal("Enter new grade: ");
        student.updateGrade(course, newGrade);
    }

    static void showAllGrades() {
        if (studentList.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        for (Student student : studentList) {
            student.showGrades();
        }
    }

    static Student findStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        for (Student student : studentList) {
            if (student.name.equalsIgnoreCase(name)) {
                return student;
            }
        }

        System.out.println("Student not found.");
        return null;
    }

    static int getNumber(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // clear the newline
        return num;
    }

    static double getDecimal(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double num = scanner.nextDouble();
        scanner.nextLine(); // clear the newline
        return num;
    }
}
