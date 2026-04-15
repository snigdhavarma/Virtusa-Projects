import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean done = false;
        
        while (!done) {
            System.out.print("Enter password: ");
            String p = s.nextLine();
            
            int errors = 0;
            
            if (p.length() < 8) {
                System.out.println("Too short.");
                errors++;
            }
            
            int upperCount = 0;
            int digitCount = 0;
            
            for (int i = 0; i < p.length(); i++) {
                if (Character.isUpperCase(p.charAt(i))) {
                    upperCount++;
                }
                if (Character.isDigit(p.charAt(i))) {
                    digitCount++;
                }
            }
            
            if (upperCount == 0) {
                System.out.println("Missing an uppercase letter.");
                errors++;
            }
            
            if (digitCount == 0) {
                System.out.println("Missing a digit.");
                errors++;
            }
            
            if (errors == 0) {
                System.out.println("Password accepted!");
                done = true;
            } else {
                System.out.println("Please try again.\n");
            }
        }
        
        s.close();
    }
}
