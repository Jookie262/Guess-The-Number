import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

public class Play extends JPanel {

    // Declaring the Game class for changing the scene
    final private Game game;

    // Create an instance of RandomNumber class to generate a random number
    RandomNumber randomNumber = new RandomNumber();

    // Create an instance of ScoringSystem class for making points and attempts
    ScoringSystem scoringSystem = new ScoringSystem();

    // Create an instance of ScoreFiles class to handle the .txt Files
    ScoreFiles scoreFiles = new ScoreFiles();

    public Play(Game game){
        this.game = game;

        // Layout to be used in this panel
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);

        // Called the method for adding the components in JPanel
        createGUI();
    }

    // All the components are found here
    private void createGUI() {

        // Declare the variables needed
        JLabel playScore, gameText, minMaxImage , mysteryNumber, statusImage, enterButton, continueButton, backButton;
        JTextField inputText;
        JPanel gridPanel;
        int random = randomNumber.generateNumber(); // generates a random number

        // Setting up and Display the Score in the Current Game
        playScore = new JLabel("Score: " + scoreFiles.intScore("scores/current_score.txt") + "   Games: " + scoreFiles.intScore("scores/num_game.txt"));
        playScore.setFont(new Font("Arial", Font.BOLD, 18));
        playScore.setForeground(Color.WHITE);
        playScore.setBorder(new EmptyBorder(20,0,0,0));
        playScore.setAlignmentX(CENTER_ALIGNMENT);
        add(playScore);

        // Setting up and Display the Game Text
        gameText = new JLabel(new ImageIcon("res/guess_number_label.png"));
        gameText.setBorder(new EmptyBorder(5,0,0,0));
        gameText.setAlignmentX(CENTER_ALIGNMENT);
        add(gameText);

        // Setting up and Display the Minimum and Maximum Number Label Image
        minMaxImage = new JLabel(new ImageIcon("res/min_max.png"));
        minMaxImage.setAlignmentX(CENTER_ALIGNMENT);
        add(minMaxImage);

        // Setting up and Display the Mystery Number
        mysteryNumber = new JLabel("?");
        mysteryNumber.setIcon(new ImageIcon("res/mystery_square.png"));
        mysteryNumber.setHorizontalTextPosition(JLabel.CENTER);
        mysteryNumber.setFont(new Font("Arial", Font.BOLD, 120));
        mysteryNumber.setForeground(new Color(62,59,80));
        mysteryNumber.setAlignmentX(CENTER_ALIGNMENT);
        add(mysteryNumber);

        // Setting up and Display the Status of the Inputted Text
        statusImage = new JLabel(new ImageIcon("res/input_number.png"));
        statusImage.setAlignmentX(CENTER_ALIGNMENT);
        statusImage.setBorder(BorderFactory.createEmptyBorder(-10, 0, 15, 0));
        add(statusImage);

        // Setting up another JPanel to make the components place beside each other
        gridPanel = new JPanel();
        gridPanel.setMaximumSize(new Dimension(260, 50));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        gridPanel.setLayout(new GridLayout(0, 2));
        gridPanel.setOpaque(false);

        // Setting up and Display the Input JTextfield
        inputText = new JTextField();
        inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        inputText.setBackground(new Color(253,233,180));
        inputText.setForeground(new Color(62,59,80));
        inputText.setHorizontalAlignment(JTextField.CENTER);
        inputText.setFont(new Font("Arial", Font.BOLD, 28));
        gridPanel.add(inputText);

        // Setting up and Display the Enter Button
        enterButton = new JLabel(new ImageIcon("res/enter_button.png"));
        enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gridPanel.add(enterButton);

        // Add the gridPanel to this JPanel
        add(gridPanel);

        // Setting up the Continue Button without displaying
        continueButton = new JLabel(new ImageIcon("res/continue_button.png"));
        continueButton.setAlignmentX(CENTER_ALIGNMENT);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setVisible(false);
        continueGame(continueButton);
        add(continueButton);

        // Setting up and Display the Back to Menu Button
        backButton = new JLabel(new ImageIcon("res/back_to_menu_button.png"));
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        linkMenu(backButton);
        add(backButton);

        // When user click the enter button
        enterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeStatus(inputText, mysteryNumber, random, statusImage, gridPanel, continueButton, backButton);
            }
        });

        // When user hits the button key while inputting in text field
        inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStatus(inputText, mysteryNumber, random, statusImage, gridPanel, continueButton, backButton);
            }
        });
    }

    // Method for Linking to Play Section
    public void linkMenu(JLabel jLabel){
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Want to Stop the Game?", "Are you Sure", JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0) {
                    // Insert the current score and number of games played to High Score when quiting the game
                    scoreFiles.compareScore("scores/high_score.txt", "scores/current_score.txt", "scores/num_game.txt");
                    game.showView(new Menu(game));
                }
            }
        });
    }

    // Method to continue a game
    public void continueGame(JLabel jLabel){
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.showView(new Play(game));
            }
        });
    }

    // For changing the background of JPanel
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("res/background.jpg").getImage(), 0, 0, null);
    }

    // Method for Changing the Status Image
    private void changeStatus(JTextField input, JLabel mysterynum, int randnum, JLabel status, JPanel gridPanel, JLabel contButton, JLabel backButton){
        // If the random number and the user input is correct
        if (String.valueOf(randnum).equals(input.getText())) {
            // Change the image of status
            status.setIcon(new ImageIcon("res/correct.png"));
            // Change the value of ? to the number
            mysterynum.setText(input.getText());
            // Hide the Panel with the enter button and text field
            gridPanel.setVisible(false);
            // Set and Show the continue button (for playing again)
            contButton.setVisible(true);
            contButton.setBorder(new EmptyBorder(-15,0,0,0));
            // Move the back button to top
            backButton.setBorder(new EmptyBorder(-9,0,0,0));
            // Get the points(based on number of attempts) and update the score
            scoringSystem.scoreAttempt();
            // Overwrite the txt score plus the current_score
            scoreFiles.write("scores/current_score.txt", scoreFiles.intScore("scores/current_score.txt") + scoringSystem.getScore());
            // Set how many games played continuously
            scoreFiles.write("scores/num_game.txt", scoreFiles.intScore("scores/num_game.txt") + 1);
            // If the input is correct, play the sound effect for correct
            sound_effect("res/correct.wav");
        } else {
            // If the input is wrong, play the sound effect for wrong
            sound_effect("res/wrong.wav");
            // Catch any possible error if the user didn't input a number
            try {
                // Convert the user input number (string) to int
                int textToInt = Integer.parseInt(input.getText());
                // Comparing the user input to the random number
                if(textToInt > 50 || textToInt < 1) {
                    // If the user input higher than 50 and lower than 1, executed this block of code
                    status.setIcon(new ImageIcon("res/out_of_range.png"));
                } else if (textToInt > randnum ){
                    // If the user input higher than random number, executed this block of code
                    status.setIcon(new ImageIcon("res/too_high.png"));
                } else if(textToInt < randnum){
                    // If the user input lower than random number, executed this block of code
                    status.setIcon(new ImageIcon("res/too_low.png"));
                }
            } catch (NumberFormatException ex) {
                // Is user input anything aside from numbers, executed this block of code
                status.setIcon(new ImageIcon("res/invalid_input.png"));
            }
        }
        // Remove the Text in text field once the user call this method
        input.setText("");
        // Increase the number of attempts
        scoringSystem.incrementAttempt();

    }

    //// Method For Sound Effects Music
    public void sound_effect(String soundName) {
        try {
            // Setting up the Audio for Background Music
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File(soundName));
            Clip clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream2);
            // Adjust Sound
            FloatControl gainControl =  (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            // Start the Audio
            clip2.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
