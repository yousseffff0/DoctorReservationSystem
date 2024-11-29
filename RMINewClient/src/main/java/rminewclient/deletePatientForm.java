package rminewclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class deletePatientForm extends JFrame {

    private JTextField nameTextField;
    private JTextField passwordTextField;
    private JButton deleteButton;

    public deletePatientForm() {
        setTitle("Delete Patient Information");
        setSize(600, 300);

        centerFrameOnScreen();
        getContentPane().setBackground(new Color(34, 40, 49));
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(150, 50, 160, 20);
        setLabelStyles(nameLabel);
        add(nameLabel);

        nameTextField = createStyledTextField();
        nameTextField.setBounds(250, 50, 150, 30);
        add(nameTextField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 90, 160, 20);
        setLabelStyles(passwordLabel);
        add(passwordLabel);

        passwordTextField = createStyledTextField();
        passwordTextField.setBounds(250, 90, 150, 30);
        add(passwordTextField);


        deleteButton = new JButton("Delete Patient");
        setJButtonStyles(deleteButton);
        add(deleteButton);

        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    public void setJButtonStyles(JButton deleteButton) {
        deleteButton.setBounds(250, 150, 150, 30);
        deleteButton.setBackground(new Color(123, 168, 192));
        deleteButton.setBorderPainted(false);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setContentAreaFilled(true);
        deleteButton.setFocusPainted(false);

        // Set text color
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Set preferred size
        deleteButton.setPreferredSize(new Dimension(150, 30));

        // Mouse hover effect
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(173, 216, 230));  // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(new Color(123, 168, 192));  // Restore original color on exit
            }
        });
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(180, 180, 180));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            }
        };

        textField.setBorder(BorderFactory.createEmptyBorder());

        textField.setBackground((new Color(34, 40, 49)));
        textField.setForeground(Color.WHITE);
        textField.setBorder(new EmptyBorder(0, 5, 0, 5));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        return textField;
    }

    private JLabel setLabelStyles(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
