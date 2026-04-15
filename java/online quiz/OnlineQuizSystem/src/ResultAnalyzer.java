public class ResultAnalyzer {
    
    public static void showResults(int score, int limit) {
        System.out.println("\n--- Performance Summary ---");
        System.out.println("You got " + score + " right out of " + limit + " questions.");
        
        double percent = ((double) score / limit) * 100;
        System.out.println("Your accuracy was: " + String.format("%.2f", percent) + "%");
        
        if (percent == 100) {
            System.out.println("Feedback: Perfect score! You clearly know what you are doing. Excellent work.");
        } else if (percent >= 70) {
            System.out.println("Feedback: Good job. You passed comfortably, but there is still some room to improve.");
        } else if (percent > 0) {
            System.out.println("Feedback: Not bad, but you might want to hit the books a little harder before the real test.");
        } else {
            System.out.println("Feedback: Oh no... well, everyone has to start somewhere! Better luck next time.");
        }
        System.out.println("---------------------------\n");
    }
}
