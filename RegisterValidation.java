// Java program for Registration Validation
// This validates student registration data

import java.util.Scanner;

public class RegisterValidation {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=================================");
        System.out.println("REGISTRATION VALIDATION SYSTEM");
        System.out.println("=================================\n");
        
        // Get user input
        System.out.print("Enter ID Number: ");
        String idNumber = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Middle Name: ");
        String middleName = scanner.nextLine();
        
        System.out.print("Enter Course Level (1-4): ");
        int courseLevel = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Course (BSIT/BSCS/BSIS/ACT): ");
        String course = scanner.nextLine();
        
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        
        // Create student object
        StudentInfo student = new StudentInfo(idNumber, lastName, firstName, middleName, 
                                             courseLevel, password, email, course, address);
        
        // Validate registration
        if (validateRegistration(student, confirmPassword)) {
            System.out.println("\n✓ Registration successful!");
            student.displayInfo();
        } else {
            System.out.println("\n✗ Registration failed!");
        }
        
        scanner.close();
    }
    
    // Validation method
    public static boolean validateRegistration(StudentInfo student, String confirmPassword) {
        // Check if passwords match
        if (!student.password.equals(confirmPassword)) {
            System.out.println("Error: Passwords do not match!");
            return false;
        }
        
        // Check password length
        if (student.password.length() < 6) {
            System.out.println("Error: Password must be at least 6 characters long!");
            return false;
        }
        
        // Check if ID number is not empty
        if (student.idNumber.isEmpty()) {
            System.out.println("Error: ID Number is required!");
            return false;
        }
        
        // Check if email contains @
        if (!student.email.contains("@")) {
            System.out.println("Error: Invalid email format!");
            return false;
        }
        
        // Check course level range
        if (student.courseLevel < 1 || student.courseLevel > 4) {
            System.out.println("Error: Course level must be between 1 and 4!");
            return false;
        }
        
        return true;
    }
}

// StudentInfo class for registration
class StudentInfo {
    String idNumber;
    String lastName;
    String firstName;
    String middleName;
    int courseLevel;
    String password;
    String email;
    String course;
    String address;
    
    // Constructor
    public StudentInfo(String idNumber, String lastName, String firstName, String middleName,
                      int courseLevel, String password, String email, String course, String address) {
        this.idNumber = idNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.courseLevel = courseLevel;
        this.password = password;
        this.email = email;
        this.course = course;
        this.address = address;
    }
    
    // Display student information
    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("ID Number: " + idNumber);
        System.out.println("Name: " + firstName + " " + middleName + " " + lastName);
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + courseLevel);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }
}