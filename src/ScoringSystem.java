public class ScoringSystem {
    // Declare score and attempts variables
    private int score, attempts;

    // Assign the attempts 1 the moment this class called
    public ScoringSystem() {
        this.attempts = 1;
    }

    // Return the score
    public int getScore() {
        return score;
    }

    // Return the number of attempts
    public int getAttempts() {
        return attempts;
    }

    // The Score System
    // Add the points depending on the number of attempts
    public void scoreAttempt(){
        if(this.attempts == 1)
            this.score += 10;
        else if(this.attempts == 2)
            this.score += 9;
        else if (this.attempts == 3)
            this.score += 5;
        else if (this.attempts == 4)
            this.score += 3;
        else
            this.score++;
    }

    // Method for increasing an attempt
    public void incrementAttempt(){
        this.attempts++;
    }
}
