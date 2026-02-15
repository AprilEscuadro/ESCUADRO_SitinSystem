// Simple Java Program for CCS Sit-in Monitoring System
// This is beginner-level code with basic concepts

public class SitInSystem {
    
    // Main method - program starts here
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("CCS SIT-IN MONITORING SYSTEM");
        System.out.println("=================================");
        System.out.println();
        
        // Create a student
        Student student = new Student();
        student.idNumber = "2024-00001";
        student.firstName = "Juan";
        student.lastName = "Dela Cruz";
        student.course = "BSIT";
        student.yearLevel = 3;
        student.sitInCount = 0;
        
        // Display student info
        student.displayInfo();
        
        // Record sit-in sessions
        System.out.println("\nRecording sit-in sessions...");
        student.recordSitIn();
        student.recordSitIn();
        student.recordSitIn();
        
        // Check progress
        student.checkProgress();
        
        System.out.println("\n=================================");
    }
}

// Student class - represents a student
class Student {
    // Student properties
    String idNumber;
    String firstName;
    String lastName;
    String course;
    int yearLevel;
    int sitInCount;
    
    // Maximum sit-ins per semester
    int maxSitIns = 30;
    
    // Method to display student information
    void displayInfo() {
        System.out.println("Student Information:");
        System.out.println("ID Number: " + idNumber);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + yearLevel);
        System.out.println("Current Sit-ins: " + sitInCount + "/" + maxSitIns);
    }
    
    // Method to record a sit-in session
    void recordSitIn() {
        if (sitInCount < maxSitIns) {
            sitInCount++;
            System.out.println("✓ Sit-in recorded! Total: " + sitInCount);
        } else {
            System.out.println("✗ Maximum sit-ins reached (30 per semester)!");
        }
    }
    
    // Method to check progress
    void checkProgress() {
        System.out.println("\n--- Progress Report ---");
        System.out.println("Student: " + firstName + " " + lastName);
        System.out.println("Completed: " + sitInCount + " out of " + maxSitIns);
        
        int remaining = maxSitIns - sitInCount;
        
        if (remaining > 0) {
            System.out.println("Remaining: " + remaining + " sit-ins available");
            double percentage = (sitInCount * 100.0) / maxSitIns;
            System.out.println("Progress: " + String.format("%.1f", percentage) + "%");
        } else {
            System.out.println("Status: MAXIMUM REACHED! ✓");
        }
    }
    
    // Method to get student status
    String getStatus() {
        if (sitInCount >= maxSitIns) {
            return "MAXIMUM REACHED";
        } else if (sitInCount >= maxSitIns * 0.75) {
            return "ON TRACK";
        } else if (sitInCount >= maxSitIns * 0.5) {
            return "MODERATE";
        } else {
            return "LOW ATTENDANCE";
        }
    }
}