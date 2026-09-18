package Joelou_Salidaga_V;

import java.util.Scanner;

public class Student {

    static final int MAX = 100;

    static class StudentInfo {
        private String name, id, program, date, document, staff;

        public StudentInfo(String name, String id, String program,
                           String date, String document, String staff) {
            this.name = name;
            this.id = id;
            this.program = program;
            this.date = date;
            this.document = document;
            this.staff = staff;
        }

        public void printInfo(int index) {
            System.out.println("\nStudent #" + index);
            System.out.println("Name: " + name);
            System.out.println("ID: " + id);
            System.out.println("Program: " + program);
            System.out.println("Date: " + date);
            System.out.println("Document: " + document);
            System.out.println("Staff: " + staff);
        }
    }

    public static String getNonEmptyInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ Field cannot be empty.");
        }
    }

    public static int getValidChoice(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice >= min && choice <= max) return choice;
            } else {
                sc.nextLine();
            }
            System.out.println("❌ Enter a number between " + min + " and " + max);
        }
    }

    public static String selectProgram(int choice) {
        return switch (choice) {
            case 1 -> "Accounting Business Management";
            case 2 -> "Humanities and Social Science";
            case 3 -> "Science Technology Engineering and Mathematics";
            case 4 -> "General Academic Strand";
            default -> "Unknown";
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentInfo[] students = new StudentInfo[MAX];
        int count = 0;
        char again;

        do {
            System.out.println("\n=== STUDENT REGISTRATION ===");

            String name = getNonEmptyInput(sc, "Enter Student Name: ");
            String id = getNonEmptyInput(sc, "Enter Student ID: ");
            String date = getNonEmptyInput(sc, "Enter Date (MM-DD-YYYY): ");

            System.out.println("\nSelect Program:");
            System.out.println("1 - ABM");
            System.out.println("2 - HUMSS");
            System.out.println("3 - STEM");
            System.out.println("4 - GAS");

            String program = selectProgram(getValidChoice(sc, "Choice: ", 1, 4));

            System.out.println("\nDocument Provided:");
            System.out.println("1 - Photocopy ID");
            System.out.println("2 - Birth Certificate");

            String document =
                    getValidChoice(sc, "Choice: ", 1, 2) == 1
                            ? "Photocopy ID"
                            : "Birth Certificate";

            String staff = getNonEmptyInput(sc, "Enter Staff Name: ");

            students[count++] = new StudentInfo(name, id, program, date, document, staff);

            while (true) {
                System.out.print("\nRegister another student? (Y/N): ");
                String input = sc.nextLine().trim().toUpperCase();
                if (input.equals("Y") || input.equals("N")) {
                    again = input.charAt(0);
                    break;
                }
                System.out.println("❌ Enter Y or N only.");
            }

        } while (again == 'Y' && count < MAX);

        System.out.println("\n===== REGISTERED STUDENTS =====");
        for (int i = 0; i < count; i++) {
            students[i].printInfo(i + 1);
        }

        sc.close();
    }
}
