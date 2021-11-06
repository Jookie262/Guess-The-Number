import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    // create a JPanel inside the JFrame
    private JPanel viewPanel;

    // Constructor of the Class
    public Game() {

        // Initialize the viewPanel the moment Game Class is called
        viewPanel = new JPanel(new BorderLayout());

        // Setting up the Game
        this.setTitle("Guess the Number"); // Title
        this.setPreferredSize(new Dimension(350, 660)); // Dimension
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the Application when clicking x
        this.add(viewPanel, BorderLayout.CENTER); // Add the JPanel in this frame
        this.setVisible(true); // To view the window
        this.pack(); // To size the components(button, img) optimally
        this.setResizable(false); // To avoid resizing the window
        this.setLocationRelativeTo(null); // To set the window in the center
    }

    // Method To View the Panel being assigned
    public void showView(JPanel jPanel){
        viewPanel.removeAll();
        viewPanel.add(jPanel, BorderLayout.CENTER);
        viewPanel.revalidate();
        viewPanel.repaint();
    }
}
