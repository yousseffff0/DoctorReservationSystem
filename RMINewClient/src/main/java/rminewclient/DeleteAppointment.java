package rminewclient;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.Frame;
import java.awt.event.*;



public class DeleteAppointment extends Frame {
    private JTextField appointmentIdTextField;
    private JButton deleteButton;

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public DeleteAppointment() {




        setTitle("Delete Appointment Information");
        setSize(600, 450);


        centerFrameOnScreen();
        setBackground(new Color(34, 40, 49));
        setLayout(null);
        
        
        JLabel appointmentIdLabel = new JLabel("Appointment ID");
        appointmentIdLabel.setBounds(100, 150, 160, 20);
        setLabelStyles(appointmentIdLabel);
        add(appointmentIdLabel);

        appointmentIdTextField = new JTextField();
        appointmentIdTextField = createStyledTextField();
        appointmentIdTextField.setBounds(250, 150, 150, 30);
        add(appointmentIdTextField);
        
      
        deleteButton = new JButton();
        deleteButton.setText("Delete Appointment");
        setJButtonStyles(deleteButton);
        setDeleteButton(deleteButton);
        add(deleteButton);



        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void setJButtonStyles(JButton Button) {
        Button.setBackground(new Color(123, 168, 192));
        Button.setBounds(200, 250, 200,30);
        Button.setBorderPainted(false);
        Button.setForeground(Color.WHITE);
        Button.setContentAreaFilled(true);
        Button.setFocusPainted(false);


        Button.setFont(new Font("Arial", Font.PLAIN, 14));

        Button.setPreferredSize(new Dimension(150, 30));

        Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Button.setBackground(new Color(173, 216, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Button.setBackground(new Color(123, 168, 192));
            }
        });
    }

    public JTextField getAppointmentIdTextField() {
        return appointmentIdTextField;
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