package rminewclient;

import javax.swing.*;
import java.awt.*;

public class FeedbackGUI extends JFrame {
    private JButton allButton;
    private JButton positiveButton;
    private JButton neutralButton;
    private JButton negativeButton;
    

    public FeedbackGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        allButton = createStyledButton("Show All Feedbacks");
        positiveButton = createStyledButton("Show All Positive Feedbacks");
        neutralButton = createStyledButton("Show All Neutral Feedbacks");
        negativeButton = createStyledButton("Show All Negative Feedbacks");
        

        // Set background color for the panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 40, 49)); // Dark grayish-blue color
        panel.setLayout(new GridLayout(4, 1, 0, 10)); // Added vertical gap of 10 pixels
        panel.add(allButton);
        panel.add(positiveButton);
        panel.add(neutralButton);
        panel.add(negativeButton);
        

        // Set background color for the frame
        getContentPane().setBackground(new Color(34, 40, 49)); // Dark grayish-blue color
        add(panel);

        // Center the frame on the screen
        centerFrameOnScreen(this);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 152, 219)); // Cool blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 25)); // Smaller button size
        return button;
    }

    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        int frameX = centerX - frame.getWidth() / 2;
        int frameY = centerY - frame.getHeight() / 2;

        frame.setLocation(frameX, frameY);
    }

    public JButton getAllButton() {
        return allButton;
    }

    public JButton getPositiveButton() {
        return positiveButton;
    }

    public JButton getNeutralButton() {
        return neutralButton;
    }

    public JButton getNegativeButton() {
        return negativeButton;
    }

    
}