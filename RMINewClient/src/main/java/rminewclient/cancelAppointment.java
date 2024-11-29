package rminewclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cancelAppointment extends JFrame {
    private JTextArea recordsArea;
//    private JButton closeButton;
    private JTextField nameTextField;
    private JTextField passwordTextField;
    private JTextField appointmentIdField;

    private JButton deleteButton;

    public cancelAppointment() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLayout(null);

        recordsArea = new JTextArea();
        recordsArea.setBounds(220, 30, 250,350);
        recordsArea.setEditable(false);
        recordsArea.setForeground(Color.WHITE);
        recordsArea.setBackground(new Color(44, 62, 80));



        JScrollPane scrollPane = new JScrollPane(recordsArea);
        setScrollPaneStyles(scrollPane);
        scrollPane.setBounds(220, 30, 350, 350);
//        add(recordsArea);
        add(scrollPane);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(220, 400, 160, 20);
        setLabelStyles(nameLabel);
        add(nameLabel);

        nameTextField = createStyledTextField();
        nameTextField.setBounds(350, 400, 200, 30);
        add(nameTextField);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(220, 450, 150, 20);
        setLabelStyles(passwordLabel);
        add(passwordLabel);

        passwordTextField = createStyledTextField();
        passwordTextField.setBounds(350, 450, 200, 30);
        add(passwordTextField);

        JLabel idLabel = new JLabel("Appointment ID");
        idLabel.setBounds(220, 500, 150, 20);
        setLabelStyles(idLabel);
        add(idLabel);

        appointmentIdField = createStyledTextField();
        appointmentIdField.setBounds(350, 500, 200, 30);
        add(appointmentIdField);

        deleteButton = new JButton("Delete Booking");
        setJButtonStyles(deleteButton);
        setDeleteButton(deleteButton);
        add(deleteButton);





        getContentPane().setBackground(new Color(44, 62, 80));


        centerFrameOnScreen();

        setVisible(true);
    }

    public JTextArea getRecordsArea() {
        return recordsArea;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    private JLabel setLabelStyles(JLabel label) {
        label.setForeground(Color.WHITE);
        Font customFont = new Font("Arial", Font.PLAIN, 14).deriveFont(15f);
        label.setFont(customFont);
        return label;
    }

    private JScrollPane setScrollPaneStyles(JScrollPane scrollPane) {


        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(Color.BLACK)
        ));
        scrollPane.setBackground(Color.DARK_GRAY);


        return scrollPane;
    }


    public void setJButtonStyles(JButton cancelButton) {
        cancelButton.setBounds(350, 600, 200, 30);
        cancelButton.setBackground(new Color(123, 168, 192));
        cancelButton.setBorderPainted(false);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setContentAreaFilled(true);
        cancelButton.setFocusPainted(false);

        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));

        cancelButton.setPreferredSize(new Dimension(150, 30));

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setBackground(new Color(173, 216, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelButton.setBackground(new Color(123, 168, 192));
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
    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JTextField getAppointmentIdField() {
        return appointmentIdField;
    }

    public void setAppointmentIdField(JTextField appointmentIdField) {
        this.appointmentIdField = appointmentIdField;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setPatientCredentials(String username, String password) {

        nameTextField.setText(username);
        passwordTextField.setText(password);
    }
}