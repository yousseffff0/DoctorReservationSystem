package rminewclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientLogInForm extends JFrame {

    private JTextField nameTextField;
    private JTextField passwordTextField;
    private JTextField valueToUpdateField;
    private JComboBox<Integer> choiceBox;
    private JButton logInButton;

    public PatientLogInForm() {
        setTitle("Update Patient Information");
        setSize(600, 350);

        getContentPane().setBackground(new Color(34, 40, 49));
        centerFrameOnScreen();
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(150, 80, 160, 20);
        setLabelStyles(nameLabel);
        add(nameLabel);

        nameTextField = createStyledTextField();
        nameTextField.setBounds(250, 80, 150, 30);
        add(nameTextField);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 130, 100, 20);
        setLabelStyles(passwordLabel);
        add(passwordLabel);

        passwordTextField = createStyledTextField();
        passwordTextField.setBounds(250, 130, 150, 30);
        add(passwordTextField);



        logInButton = new JButton("Log In");
        setJButtonStyles(logInButton);
        add(logInButton);

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

    public void setJButtonStyles(JButton addButton) {
        addButton.setBounds(250, 200, 150, 30);
        addButton.setBackground(new Color(123, 168, 192));
        addButton.setBorderPainted(false);
        addButton.setForeground(Color.WHITE);
        addButton.setContentAreaFilled(true);
        addButton.setFocusPainted(false);


        addButton.setFont(new Font("Arial", Font.PLAIN, 14));

        addButton.setPreferredSize(new Dimension(150, 30));

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setBackground(new Color(173, 216, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addButton.setBackground(new Color(123, 168, 192));
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
        Font customFont = new Font("Arial", Font.PLAIN, 14).deriveFont(15f);
        label.setFont(customFont);
        return label;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }


    public JTextField getPasswordTextField() {
        return passwordTextField;
    }


    public JTextField getValueToUpdateField() {
        return valueToUpdateField;
    }

    public JComboBox<Integer> getChoiceBox() {
        return choiceBox;
    }

    public JButton getLogInButton() {
        return logInButton;
    }
}

