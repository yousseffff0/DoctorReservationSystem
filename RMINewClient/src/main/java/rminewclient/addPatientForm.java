package rminewclient;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.Frame;
import java.awt.event.*;



public class addPatientForm extends Frame {
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField phoneNumTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JTextField insuranceNameField;
    private JTextField insuranceNumberField;

    private JTextField balanceField;
    private JButton addButton;

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public addPatientForm() {




        setTitle("Add Patient Information");
        setSize(600, 500);


        centerFrameOnScreen();
        setBackground(new Color(34, 40, 49));
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(120, 50, 160, 20);
        setLabelStyles(nameLabel);
        add(nameLabel);

        nameTextField = createStyledTextField();
        nameTextField.setBounds(250, 50, 150, 30);
        add(nameTextField);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(120, 90, 160, 20);
        setLabelStyles(ageLabel);
        add(ageLabel);

        ageTextField = createStyledTextField();
        ageTextField.setBounds(250, 90, 150, 30);
        add(ageTextField);

        JLabel phoneNumLabel = new JLabel("number");
        phoneNumLabel.setBounds(120, 130, 160, 20);
        setLabelStyles(phoneNumLabel);
        add(phoneNumLabel);

        phoneNumTextField = createStyledTextField();
        phoneNumTextField.setBounds(250, 130, 150, 30);
        add(phoneNumTextField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(120, 170, 160, 20);
        setLabelStyles(emailLabel);
        add(emailLabel);

        emailTextField = createStyledTextField();
        emailTextField.setBounds(250, 170, 150, 30);
        add(emailTextField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(120, 210, 100, 20);
        setLabelStyles(passwordLabel);
        add(passwordLabel);

        passwordTextField = createStyledTextField();
        passwordTextField.setBounds(250, 210, 150, 30);
        add(passwordTextField);

        JLabel balanceLabel = new JLabel("Balance");
        balanceLabel.setBounds(120, 250, 160, 20);

        setLabelStyles(balanceLabel);
        add(balanceLabel);

        balanceField = createStyledTextField();
        balanceField.setBounds(250, 250, 150, 30);
        add(balanceField);

        JLabel insuranceNameLabel = new JLabel("insurance name");
        insuranceNameLabel.setBounds(120, 290, 160, 20);
        setLabelStyles(insuranceNameLabel);
        add(insuranceNameLabel);

        insuranceNameField = createStyledTextField();
        insuranceNameField.setBounds(250, 290, 150, 30);
        add(insuranceNameField);

        JLabel insuranceNumberLabel = new JLabel("insurance number");
        insuranceNumberLabel.setBounds(120, 330, 160, 20);
        setLabelStyles(insuranceNumberLabel);

        add(insuranceNumberLabel);

        insuranceNumberField = createStyledTextField();
        insuranceNumberField.setBounds(250, 330, 150, 30);
        add(insuranceNumberField);

        addButton = new JButton();
        setJButtonStyles(addButton);
        addButton.setText("Add Patient");
        add(addButton);



        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void setJButtonStyles(JButton addButton) {
        addButton.setBounds(250, 400, 150, 30);
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










    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getAgeTextField() {
        return ageTextField;
    }

    public JTextField getPhoneNumTextField() {
        return phoneNumTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JTextField getInsuranceNameField() {
        return insuranceNameField;
    }

    public JTextField getInsuranceNumberField() {
        return insuranceNumberField;
    }

    public JTextField getBalanceField() {
        return balanceField;
    }

    public JButton getAddButton() {
        return addButton;
    }


    private void centerFrameOnScreen() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;


        setLocation(x, y);
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
}