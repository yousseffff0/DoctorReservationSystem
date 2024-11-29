package rminewclient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 300);

        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Set background color
        Color backgroundColor = new Color(44, 62, 80); // Dark background
        getContentPane().setBackground(backgroundColor);

        // Set text color
        Color textColor = Color.BLACK;
        emailField.setForeground(textColor);
        passwordField.setForeground(textColor);
        loginButton.setForeground(textColor);

        // Set button color
        Color buttonColor = new Color(52, 152, 219); // Dark blue button
        loginButton.setBackground(buttonColor);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBackground(backgroundColor);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(textColor);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColor);

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        getContentPane().add(panel);

        centerFrameOnScreen(this);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action handled in the controller
            }
        });

        setVisible(true);
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        int frameX = centerX - frame.getWidth() / 2;
        int frameY = centerY - frame.getHeight() / 2;

        frame.setLocation(frameX, frameY);
    }
}