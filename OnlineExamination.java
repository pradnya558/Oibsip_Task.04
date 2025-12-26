
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExamination {
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = "Student_01";
    private static String password = "password123";
    private static boolean isSubmitted = false;

    public static void main(String[] args) {
        if (login()) {
            showMenu();
        }
    }

    // 1. Login Functionality
    public static boolean login() {
        System.out.println("--- ONLINE EXAMINATION LOGIN ---");
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        if (user.equals(currentUser) && pass.equals(password)) {
            System.out.println("Login Successful!\n");
            return true;
        } else {
            System.out.println("Invalid Credentials.");
            return false;
        }
    }

    public static void showMenu() {
        while (true) {
            System.out.println("1. Update Profile/Password\n2. Start Exam\n3. Logout");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> updateProfile();
                case 2 -> startExam();
                case 3 -> {
                    System.out.println("Logging out...");
                    System.exit(0);
                }
            }
        }
    }

    // 2. Update Profile and Password
    public static void updateProfile() {
        System.out.print("Enter new password: ");
        password = scanner.nextLine();
        System.out.println("Profile updated successfully!\n");
    }

    // 3 & 4. MCQ Selection, Timer, and Auto-Submit
    public static void startExam() {
        isSubmitted = false;
        Timer timer = new Timer();
        
        // Setting a 30-second timer for the exam
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isSubmitted) {
                    System.out.println("\n--- TIME IS UP! Auto-submitting your exam. ---");
                    isSubmitted = true;
                    // In a real app, you'd trigger the submission logic here
                }
            }
        }, 30000); // 30,000 milliseconds = 30 seconds

        System.out.println("Exam started. You have 30 seconds.");
        System.out.println("Q1: What is the capital of Java (Programming)?");
        System.out.println("A) Coffee  B) Object  C) Method");
        
        System.out.print("Your Answer: ");
        String answer = scanner.nextLine();

        if (!isSubmitted) {
            isSubmitted = true;
            timer.cancel(); // Stop the timer if submitted manually
            System.out.println("Exam submitted successfully! Your answer: " + answer);
        }
    }
}