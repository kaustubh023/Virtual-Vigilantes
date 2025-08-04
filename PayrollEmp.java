import java.io.*;
import java.util.*;


abstract class Employee {
    String name;
    double salary;
    String type;

    Employee(String name, double salary, String type) {
        this.name = name;
        this.salary = salary;
        this.type = type;
    }

    abstract double calculateMonthlyPay();

    String getDetails() {
        return "Name: " + name + ", Type: " + type + ", Base Salary: ₹" + salary;
    }

    String toFileString() {
        return name + "," + salary + "," + type;
    }
}


class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name, double salary) {
        super(name, salary, "Full-Time");
    }

    @Override
    double calculateMonthlyPay() {
        double bonus = 0.1 * salary;
        double deduction = 0.05 * salary;
        return salary + bonus - deduction;
    }
}


class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name, double salary) {
        super(name, salary, "Part-Time");
    }

    @Override
    double calculateMonthlyPay() {
        double bonus = 0.05 * salary;
        double deduction = 0.02 * salary;
        return salary + bonus - deduction;
    }
}






public class PayrollEmp {
    static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        loadEmployees(employees);

        while (true) {
            System.out.println("\n----- Employee Payroll System -----");
            System.out.println("1. Add Employee");
            System.out.println("2. Show Payroll");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                addEmployee(scanner, employees);
            } else if (choice == 2) {
                showPayroll(employees);
            } else if (choice == 3) {
                saveEmployees(employees);
                System.out.println("Thank you! Data saved.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }


    static void addEmployee(Scanner scanner, List<Employee> employees) {
        System.out.print("Enter employee name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Is employee Full-Time or Part-Time? (F/P): ");
        char type = scanner.next().toUpperCase().charAt(0);

        Employee emp;
        if (type == 'F') {
            emp = new FullTimeEmployee(name, salary);
        } else {
            emp = new PartTimeEmployee(name, salary);
        }

        employees.add(emp);
        System.out.println("Employee added successfully!");
    }


    static void showPayroll(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employee data found.");
            return;
        }

        System.out.println("\n----- Payroll Summary -----");
        for (Employee emp : employees) {
            System.out.println(emp.getDetails());
            System.out.println("Monthly Pay: ₹" + emp.calculateMonthlyPay());
            System.out.println("-----------------------------");
        }
    }

  
    static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employees) {
                writer.write(emp.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving employee data.");
        }
    }

    // Load employees from file
    static void loadEmployees(List<Employee> employees) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                String name = parts[0];
                double salary = Double.parseDouble(parts[1]);
                String type = parts[2];

                Employee emp = type.equals("Full-Time")
                        ? new FullTimeEmployee(name, salary)
                        : new PartTimeEmployee(name, salary);
                employees.add(emp);
            }
        } catch (IOException e) {
            System.out.println("Error loading employee data.");
        }
    }
}
