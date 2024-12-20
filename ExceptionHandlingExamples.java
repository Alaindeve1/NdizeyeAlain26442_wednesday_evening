// Exception Handling Examples in Java
// Author: [Ndizeye Alain]

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ExceptionHandlingExamples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulate each exception
        simulateIOException(scanner);
        simulateFileNotFoundException(scanner);
        simulateEOFException();
        simulateSQLException();
        simulateClassNotFoundException(scanner);
        simulateArithmeticException(scanner);
        simulateNullPointerException();
        simulateArrayIndexOutOfBoundsException(scanner);
        simulateClassCastException();
        simulateIllegalArgumentException(scanner);
        simulateNumberFormatException(scanner);
    }

    // Checked Exceptions

    // 1. IOException
    private static void simulateIOException(Scanner scanner) {
        try {
            System.out.print("Enter a file name to read: ");
            String fileName = scanner.nextLine();
            if (fileName.isEmpty()) {
                throw new IllegalArgumentException("File name cannot be empty.");
            }
            FileReader reader = new FileReader(fileName);
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // 2. FileNotFoundException
    private static void simulateFileNotFoundException(Scanner scanner) {
        try {
            System.out.print("Enter a file name to open: ");
            String fileName = scanner.nextLine();
            if (fileName.isEmpty()) {
                throw new IllegalArgumentException("File name cannot be empty.");
            }
            FileInputStream file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException caught: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // 3. EOFException
    private static void simulateEOFException() {
        try (ByteArrayInputStream input = new ByteArrayInputStream(new byte[]{}); 
             ObjectInputStream ois = new ObjectInputStream(input)) {
            ois.readObject();
        } catch (EOFException e) {
            System.out.println("EOFException caught: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    // 4. SQLException
    private static void simulateSQLException() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:invalid:url", "user", "pass");
        } catch (SQLException e) {
            System.out.println("SQLException caught: " + e.getMessage());
        }
    }

    // 5. ClassNotFoundException
    private static void simulateClassNotFoundException(Scanner scanner) {
        try {
            System.out.print("Enter a class name to load: ");
            String className = scanner.nextLine();
            if (className.isEmpty()) {
                throw new IllegalArgumentException("Class name cannot be empty.");
            }
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException caught: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // Unchecked Exceptions

    // 6. ArithmeticException
    private static void simulateArithmeticException(Scanner scanner) {
        try {
            int numerator = getValidatedInteger(scanner, "Enter a numerator: ");
            int denominator = getValidatedInteger(scanner, "Enter a denominator: ");
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: " + e.getMessage());
        }
    }

    // 7. NullPointerException
    private static void simulateNullPointerException() {
        try {
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        }
    }

    // 8. ArrayIndexOutOfBoundsException
    private static void simulateArrayIndexOutOfBoundsException(Scanner scanner) {
        try {
            int size = getValidatedInteger(scanner, "Enter an array size: ");
            if (size <= 0) {
                throw new IllegalArgumentException("Array size must be greater than zero.");
            }
            int[] arr = new int[size];
            int index = getValidatedInteger(scanner, "Enter an index to access: ");
            int value = arr[index];
            System.out.println("Value at index " + index + ": " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // 9. ClassCastException
    private static void simulateClassCastException() {
        try {
            Object obj = new Integer(10);
            String str = (String) obj;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught: " + e.getMessage());
        }
    }

    // 10. IllegalArgumentException
    private static void simulateIllegalArgumentException(Scanner scanner) {
        try {
            System.out.print("Enter a sleep duration in milliseconds: ");
            if (!scanner.hasNextLong()) {
                scanner.next(); // Clear invalid input
                throw new IllegalArgumentException("Duration must be a long integer.");
            }
            long duration = scanner.nextLong();
            if (duration < 0) {
                throw new IllegalArgumentException("Duration cannot be negative.");
            }
            Thread.sleep(duration);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught: " + e.getMessage());
        }
    }

    // 11. NumberFormatException
    private static void simulateNumberFormatException(Scanner scanner) {
        try {
            System.out.print("Enter a number: ");
            String input = scanner.next();
            if (!input.matches("\\d+")) {
                throw new IllegalArgumentException("Input must be a number.");
            }
            int num = Integer.parseInt(input);
            System.out.println("Parsed number: " + num);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException caught: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // Helper method to get validated integer input
    private static int getValidatedInteger(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                System.out.println("Input must be an integer. Please try again.");
                scanner.next(); // Clear invalid input
                continue;
            }
            value = scanner.nextInt();
            break;
        }
        return value;
    }
}
