package rminewclient;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.Frame;
import java.awt.event.*;



public class AddAppointment extends Frame {
    private JTextField doctorNameTextField;
    
    private JTextField dateTextField;
    private JTextField timeTextField;
    private JTextField dayTextField;
   

    
    private JButton addButton;

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public AddAppointment() {




        setTitle("Add Appointment Information");
        setSize(600, 450);


        centerFrameOnScreen();
        setBackground(new Color(34, 40, 49));
        setLayout(null);
        
        
        JLabel docotrNameLabel = new JLabel("Doctor Name");
        docotrNameLabel.setBounds(120, 100, 160, 20);
        setLabelStyles(docotrNameLabel);
        add(docotrNameLabel);

        doctorNameTextField = new JTextField();
        doctorNameTextField = createStyledTextField();
        doctorNameTextField.setBounds(250, 100, 150, 30);
        add(doctorNameTextField);
        
       
        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(120, 150, 160, 20);
        setLabelStyles(dateLabel);
        add(dateLabel);

        dateTextField = new JTextField();
        dateTextField = createStyledTextField();
        dateTextField.setBounds(250, 150, 150, 30);

        add(dateTextField);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setBounds(120, 200, 160, 20);
        setLabelStyles(timeLabel);
        add(timeLabel);

        timeTextField = new JTextField();
        timeTextField = createStyledTextField();
        timeTextField.setBounds(250, 200, 150, 30);
        add(timeTextField);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setBounds(120, 250, 160, 20);
        setLabelStyles(dayLabel);
        add(dayLabel);

        dayTextField = new JTextField();
        dayTextField = createStyledTextField();
        dayTextField.setBounds(250, 250, 150, 30);
        add(dayTextField);

      
        addButton = new JButton();
        setJButtonStyles(addButton);
        addButton.setText("Add Appointment");
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
        addButton.setBounds(250, 350, 150, 30);
        addButton.setBackground(new Color(123, 168, 192));
        addButton.setBorderPainted(false);
        addButton.setForeground(Color.WHITE);
        addButton.setContentAreaFilled(true);

        // Set text color
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Set preferred size
        addButton.setPreferredSize(new Dimension(100, 30));

        // Mouse hover effect
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setBackground(new Color(173, 216, 230));  // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addButton.setBackground(new Color(123, 168, 192));  // Restore original color on exit
            }
        });
        setAddButton(addButton);
    }

    public JTextField getDoctorNameTextField() {
        return doctorNameTextField;
    }



    

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public JTextField getTimeTextField() {
        return timeTextField;
    }

    public JTextField getDayTextField() {
        return dayTextField;
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