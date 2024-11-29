package rminewclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateAppointment extends JFrame {

    private JTextField nameTextField;
    private JTextField passwordTextField;
    private JTextField valueToUpdateField;
    private JTextField appointmentIdTextField;
    private JComboBox<Integer> choiceBox;
    
    private JButton updateButton;

    public UpdateAppointment() {
        setTitle("Update Appointment Information");
        setSize(600, 650);

        getContentPane().setBackground(new Color(34, 40, 49));
        centerFrameOnScreen();
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(150, 40, 160, 20);
        setLabelStyles(nameLabel);
        add(nameLabel);

        nameTextField = createStyledTextField();
        nameTextField.setBounds(250, 40, 150, 30);
        add(nameTextField);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 90, 100, 20);
        setLabelStyles(passwordLabel);
        add(passwordLabel);

        passwordTextField = createStyledTextField();
        passwordTextField.setBounds(250, 90, 150, 30);
        add(passwordTextField);
        
        
        JLabel appointmentIdLabel = new JLabel("Appointment ID");
        appointmentIdLabel.setBounds(150, 140, 100, 20);
        setLabelStyles(appointmentIdLabel);
        add(appointmentIdLabel);

        appointmentIdTextField = createStyledTextField();
        appointmentIdTextField.setBounds(250, 140, 150, 30);
        add(appointmentIdTextField);
        
        

        JLabel updateValueLabel = new JLabel("Update");
        updateValueLabel.setBounds(150, 190, 150, 20);
        setLabelStyles(updateValueLabel);
        add(updateValueLabel);

        valueToUpdateField = createStyledTextField();
        valueToUpdateField.setBounds(250, 190, 150, 30);
        add(valueToUpdateField);





        JLabel instructionLabel = new JLabel("Choose 1 to update Doctor Name");
        instructionLabel.setBounds(150, 240, 400, 20);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel);
        
        JLabel instructionLabe2 = new JLabel("Choose 2 to update Date of the Appointment");
        instructionLabe2.setBounds(150, 290, 400, 20);
        instructionLabe2.setForeground(Color.WHITE);
        instructionLabe2.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabe2);
        
        
        
        

        JLabel instructionLabel3 = new JLabel("Choose 3 to update Time of the Appointment ");
        instructionLabel3.setBounds(150, 340, 400, 20);
        instructionLabel3.setForeground(Color.WHITE);
        instructionLabel3.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel3);

        JLabel instructionLabel4 = new JLabel("Choose  4 to update Day of the Appointment");
        instructionLabel4.setBounds(150, 390, 400, 20);
        instructionLabel4.setForeground(Color.WHITE);
        instructionLabel4.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel4);


        Integer[] ratingOptions = {1, 2, 3, 4};
        choiceBox = new JComboBox<>(ratingOptions);
        choiceBox.setBounds(250, 440, 150, 30);
        createStyledComboBox(choiceBox);
        add(choiceBox);


        updateButton = new JButton("Update Appointment");
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
        updateButton.setBounds(250, 500, 150, 30);
        updateButton.setBackground(new Color(123, 168, 192));
        updateButton.setBorderPainted(false);
        updateButton.setForeground(Color.WHITE);
        updateButton.setContentAreaFilled(true);

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
                g2.setColor(new Color(60, 70, 80));
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
    public JTextField getAppointmentIdTextField() {
        return appointmentIdTextField;
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