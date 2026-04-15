public class Question {
    private String prompt;
    private String[] opts;
    private int ans;

    public Question(String prompt, String[] opts, int ans) {
        this.prompt = prompt;
        this.opts = opts;
        this.ans = ans;
    }

    public String getPrompt() {
        return prompt;
    }

    public String[] getOpts() {
        return opts;
    }

    public int getAns() {
        return ans;
    }
}
