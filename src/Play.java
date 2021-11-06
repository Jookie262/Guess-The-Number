import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Play extends JPanel {

    // Declaring the Game class for changing the scene
    final private Game game;

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

        // Setting up and Display the Score in the Current Game
        playScore = new JLabel("Score: 0");
        playScore.setFont(new Font("Arial", Font.BOLD, 20));
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
        add(statusImage);

        // Setting up another JPanel to make the components place beside each other
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 2));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(12, 35, 12, 0));
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
        add(continueButton);

        // Setting up and Display the Back to Menu Button
        backButton = new JLabel(new ImageIcon("res/back_to_menu_button.png"));
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkMenu(backButton);
        add(backButton);
    }

    // Method for Linking to Play Section
    public void linkMenu(JLabel jLabel){
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.showView(new Menu(game));
            }
        });
    }

    // For changing the background of JPanel
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("res/background.jpg").getImage(), 0, 0, null);
    }

}
