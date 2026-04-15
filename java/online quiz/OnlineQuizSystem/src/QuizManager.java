import java.io.*;
import java.util.*;

public class QuizManager {
    
    private static final String fileName = "../questions.txt";
    private static final int timeLimit = 60; 

    public void addQuestion(Scanner sc) {
        System.out.println("\n--- Admin Panel ---");
        System.out.println("Let's add a new multiple-choice question.");
        
        System.out.print("Question prompt: ");
        String prompt = sc.nextLine();
        
        String[] opts = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            opts[i] = sc.nextLine();
        }
        
        int ans = -1;
        boolean valid = false;
        
        while (!valid) {
            System.out.print("Which option represents the correct answer (1-4)? ");
            try {
                ans = Integer.parseInt(sc.nextLine());
                if (ans >= 1 && ans <= 4) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("That wasn't a valid number. Try again.");
            }
        }
        
        saveToFile(prompt, opts, ans);
        System.out.println("Awesome, the question was saved successfully!");
    }

    private void saveToFile(String prompt, String[] opts, int ans) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(prompt);
            writer.newLine();
            
            for (String opt : opts) {
                writer.write(opt);
                writer.newLine();
            }
            
            writer.write(String.valueOf(ans));
            writer.newLine();
            writer.write("---"); 
            writer.newLine();
            
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the question: " + e.getMessage());
        }
    }

    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        File f = new File(fileName);
        
        if (!f.exists()) {
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String prompt = line;
                String[] opts = new String[4];
                
                for (int i = 0; i < 4; i++) {
                    opts[i] = reader.readLine();
                }
                
                int ans = Integer.parseInt(reader.readLine());
                
                reader.readLine(); 
                
                Question qObj = new Question(prompt, opts, ans);
                list.add(qObj);
            }
        } catch (Exception e) {
            System.out.println("Looks like the data file got messed up or couldn't be read.");
        }
        
        return list;
    }

    public void startQuiz(Scanner sc) {
        List<Question> questions = loadQuestions();
        
        if (questions.isEmpty()) {
            System.out.println("There are no questions available yet! Let the Admin add some first.");
            return;
        }

        Collections.shuffle(questions);
        
        int limit = Math.min(5, questions.size());
        int score = 0;
        
        System.out.println("\n--- Quiz Started! ---");
        System.out.println("You have a " + timeLimit + " second timer for the overall quiz. Don't fall asleep!");
        
        long start = System.currentTimeMillis();
        long end = start + (timeLimit * 1000L);
        
        for (int i = 0; i < limit; i++) {
            if (System.currentTimeMillis() > end) {
                System.out.println("\nTIME IS UP! You exceeded the maximum time allowed.");
                break;
            }

            Question curr = questions.get(i);
            
            System.out.println("\nQuestion " + (i + 1) + " of " + limit);
            System.out.println(curr.getPrompt());
            
            String[] opts = curr.getOpts();
            for (int k = 0; k < opts.length; k++) {
                System.out.println((k + 1) + ") " + opts[k]);
            }
            
            int userAns = -1;
            boolean answered = false;
            
            while (!answered) {
                System.out.print("Your answer (1-4): ");
                String in = sc.nextLine();
                
                if (System.currentTimeMillis() > end) {
                    System.out.println("\nTIME IS UP! We aren't counting that answer because you ran out of time.");
                    ResultAnalyzer.showResults(score, limit);
                    return; 
                }

                try {
                    userAns = Integer.parseInt(in.trim());
                    
                    if (userAns >= 1 && userAns <= 4) {
                        answered = true;
                    } else {
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Numbers only, please. Try again.");
                }
            }
            
            if (userAns == curr.getAns()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The right answer was option " + curr.getAns());
            }
        }
        
        System.out.println("\nQuiz Completed! Let's see how you did.");
        ResultAnalyzer.showResults(score, limit);
    }
}
