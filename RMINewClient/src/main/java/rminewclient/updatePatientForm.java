package rminewclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class updatePatientForm extends JFrame {

    private JTextField nameTextField;
    private JTextField passwordTextField;
    private JTextField valueToUpdateField;
    private JComboBox<Integer> choiceBox;
    private JButton updateButton;

    public updatePatientForm() {
        setTitle("Update Patient Information");
        setSize(600, 450);

        getContentPane().setBackground(new Color(34, 40, 49));
        centerFrameOnScreen();
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(150, 50, 160, 20);
        setLabelStyles(nameLabel);
        add(nameLabel);

        nameTextField = createStyledTextField();
        nameTextField.setBounds(250, 50, 150, 30);
        add(nameTextField);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 90, 100, 20);
        setLabelStyles(passwordLabel);
        add(passwordLabel);

        passwordTextField = createStyledTextField();
        passwordTextField.setBounds(250, 90, 150, 30);
        add(passwordTextField);

        JLabel updateValueLabel = new JLabel("Update");
        updateValueLabel.setBounds(150, 140, 160, 20);
        setLabelStyles(updateValueLabel);
        add(updateValueLabel);

        valueToUpdateField = createStyledTextField();
        valueToUpdateField.setBounds(250, 140, 150, 30);
        add(valueToUpdateField);




        JLabel instructionLabel = new JLabel("Choose 1 to update phone number, 2 to update email");
        instructionLabel.setBounds(150, 190, 500, 30);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel);

        JLabel instructionLabel2 = new JLabel("Choose 3 to update insurance company name, and ");
        instructionLabel2.setBounds(150, 220, 500, 30);
        instructionLabel2.setForeground(Color.WHITE);
        instructionLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel2);

        JLabel instructionLabel3 = new JLabel("Choose  4 to update insurance number");
        instructionLabel3.setBounds(150, 250, 500, 30);
        instructionLabel3.setForeground(Color.WHITE);
        instructionLabel3.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel3);


        Integer[] ratingOptions = {1, 2, 3, 4};
        choiceBox = new JComboBox<>(ratingOptions);
        choiceBox.setBounds(250, 300, 150, 30);
        createStyledComboBox(choiceBox);
        add(choiceBox);


        updateButton = new JButton("Update Patient");
        setJButtonStyles(updateButton);
        add(updateButton);

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

    public void setJButtonStyles(JButton updateButton) {
        updateButton.setBounds(250, 350, 150, 30);
        updateButton.setBackground(new Color(123, 168, 192));
        updateButton.setBorderPainted(false);
        updateButton.setForeground(Color.WHITE);
        updateButton.setContentAreaFilled(true);
        updateButton.setFocusPainted(false);


        updateButton.setFont(new Font("Arial", Font.PLAIN, 14));

        updateButton.setPreferredSize(new Dimension(150, 30));

        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                updateButton.setBackground(new Color(173, 216, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateButton.setBackground(new Color(123, 168, 192));
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

    private JComboBox<Integer> createStyledComboBox(JComboBox<Integer> comboBox) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel) {
                    JLabel label = (JLabel) renderer;
                    label.setBorder(BorderFactory.createEmptyBorder());
                    label.setBackground(new Color(34, 40, 49));
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Arial", Font.PLAIN, 14));
                }
                return renderer;
            }


        });


        comboBox.setBackground(new Color(34, 40, 49));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBorder(new EmptyBorder(0, 5, 0, 5));

        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        Integer[] ratingOptions = {1, 2, 3, 4};
        comboBox.setModel(new DefaultComboBoxModel<>(ratingOptions));

        return comboBox;
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

    public JButton getUpdateButton() {
        return updateButton;
    }
}

