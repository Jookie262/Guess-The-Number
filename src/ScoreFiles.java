import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreFiles {

    // Show the 1st Line Score in a filename and returns a String
    public String showScore(String filename){
        String score = "0";
        try{
            File text = new File(filename);
            Scanner scan = new Scanner(text);
            score = scan.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return score;
    }

    // Show the 2st Line Attempt in a filename and returns a String
    public String showGames(String filename){
        String attempt = "0";
        try{
            File text = new File(filename);
            Scanner scan = new Scanner(text);
            scan.nextLine();
            attempt = scan.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return attempt;
    }

    // Convert the showScore method to int
    public int intScore(String filename){
        return Integer.parseInt(showScore(filename));
    }

    // Convert the showsGames method to int
    public int intGames(String filename){
        return Integer.parseInt(showGames(filename));
    }

    // Overwrites the text file
    // Used in current_score.txt and num_game.txt
    public void write(String filename, int score){
        try{
            FileWriter score_writer = new FileWriter(filename);
            score_writer.write(String.valueOf(score));
            score_writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Overwrites the text file
    // Used in high_score.txt
    public void writeScoreAttempts(String filename, int score, int attemtps){
        try{
            FileWriter score_writer = new FileWriter(filename);
            score_writer.write(String.valueOf(score));
            score_writer.write("\n");
            score_writer.write(String.valueOf(attemtps));
            score_writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Compare the score if it is a high score
    public void compareScore(String high_score, String current_score, String current_played_games){
        // If the current score is higher than inside the high_score.txt file
        if (intScore(high_score) < intScore(current_score)){
            // Overwrite with new Score
            writeScoreAttempts(high_score, intScore(current_score), intScore(current_played_games));
        }
        // Else if the current score and the score inside the high_score.txt is equals
        else if(intScore(high_score) == intScore(current_score)){
            // If the number of games played by the user is lower than inside the high_score.txt
            if (intGames(high_score) > intScore(current_played_games)){
                // Overwrite with new Score
                writeScoreAttempts(high_score, intScore(current_score), intScore(current_played_games));
            }
        }
    }
}
