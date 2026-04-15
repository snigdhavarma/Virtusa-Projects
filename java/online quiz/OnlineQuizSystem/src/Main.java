import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuizManager manager = new QuizManager();
        boolean run = true;

        System.out.println("=========================================");
        System.out.println("  Welcome to the Ultimate Java Quizzer!  ");
        System.out.println("=========================================");

        while (run) {
            System.out.println("\n--- Select Your Role ---");
            System.out.println("1. Student / User");
            System.out.println("2. Administrator");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String roleChoice = sc.nextLine();

            if (roleChoice.equals("1")) {
                boolean userRun = true;
                while (userRun) {
                    System.out.println("\n--- User Menu ---");
                    System.out.println("1. Take a Quiz");
                    System.out.println("2. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    String userChoice = sc.nextLine();
                    
                    if (userChoice.equals("1")) {
                        manager.startQuiz(sc);
                    } else if (userChoice.equals("2")) {
                        userRun = false;
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                }
            } else if (roleChoice.equals("2")) {
                System.out.print("Enter Admin Password: ");
                String password = sc.nextLine();
                
                if (password.equals("admin123")) {
                    boolean adminRun = true;
                    while (adminRun) {
                        System.out.println("\n--- Admin Menu ---");
                        System.out.println("1. Add new question");
                        System.out.println("2. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        String adminChoice = sc.nextLine();
                        
                        if (adminChoice.equals("1")) {
                            manager.addQuestion(sc);
                        } else if (adminChoice.equals("2")) {
                            adminRun = false;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Incorrect password! Returning to main menu.");
                }
            } else if (roleChoice.equals("3")) {
                System.out.println("Thanks for playing! See you next time.");
                run = false;
            } else {
                System.out.println("Oops, that's not a valid option. Please try again.");
            }
        }
        
        sc.close();
    }
}
