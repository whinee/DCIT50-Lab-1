package xyz.whinyaan.dcit50.lab1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static final String INVALID_INPUT_NUMBER = "Invalid input. Please"
            + " enter a number.";

    public static final String PROMPT_CONTINUE_USING = "Do you want to continue"
            + " using the program? ";

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loop = true;

        while (loop) {
            System.out.println("--- Educational Level System ---");

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your age: ");
            String ageString = scanner.nextLine();
            int age = 0;

            try {
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_NUMBER);
                wantToContinue(PROMPT_CONTINUE_USING);
                System.out.println();
                continue;
            }

            String educationLevel = getEducationLevel(age);

            switch (educationLevel) {
                case "old":
                    System.out.println("Sorry " + name +
                    ", you are too old, and thus not eligible to be a student.");
                    break;
                case "young":
                    System.out.println("Sorry " + name +
                    ", you are too young, and thus not eligible to be a " +
                    "student.");
                    break;
                default:
                    System.out.println(name + " is in " + educationLevel + ".");
            }

            wantToContinue(PROMPT_CONTINUE_USING);
            System.out.println();
        }

    }

    public static void wantToContinue(
            String prompt, String... messagesBeforeExit) {
        boolean loop = true;

        while (loop) {
            System.out.print(prompt + "(yes/no): ");
            String answer = scanner.nextLine();
            switch (answer) {
                case "yes":
                    loop = false;
                    break;
                case "no":
                    for (String message : messagesBeforeExit) {
                        System.out.println(message);
                    }
                    exit();
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        }
    }

    public static void exit() {
        System.out.println("Thank you for using the vending machine!");
        System.exit(0);
    }

    public static String getEducationLevel(int age) {
        // https://www.nca.edu.ph/philippine-k-to-12-education-overview/
        int grade = age - 5;
        int hsLevel = age - 11;
        int collegeLevel = age - 17;

        if (age > 100) {
            return "old";
        } else if (age == 5) {
            return "Kindergarten";
        } else if (age >= 6 && age <= 11) {
            return "Elementary School (Grade " + grade + ")";
        } else if (age >= 12 && age <= 15) {
            return "Junior High School (Grade " + grade + ", " +
            toOrdinal(hsLevel) + " year)";
        } else if (age >= 16 && age <= 17) {
            return "Senior High School (Grade " + grade + ", " +
            toOrdinal(hsLevel) + " year)";
        } else if (age >= 18 && age <= 22) {
            return "College (" + toOrdinal(collegeLevel) + " year)";
        } else if (age >= 23) {
            return "College";
        } else {
            return "young";
        }
    }

    public static String toOrdinal(int number) {
        if (number <= 0) {
            return String.valueOf(number);
        }

        int lastDigit = number % 10;
        int lastTwoDigits = number % 100;

        String suffix;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 13) {
            suffix = "th";
        } else if (lastDigit == 1) {
            suffix = "st";
        } else if (lastDigit == 2) {
            suffix = "nd";
        } else if (lastDigit == 3) {
            suffix = "rd";
        } else {
            suffix = "th";
        }

        return number + suffix;
    }
}
