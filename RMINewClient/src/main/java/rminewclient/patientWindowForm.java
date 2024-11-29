package rminewclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class patientWindowForm extends JFrame {

    private JButton addPatientButton;
    private JButton deletePatientButton;
    private JButton updatePatientButton;
    private JButton bookingButton;
    private JButton cancelBookingButton;

    public patientWindowForm() {
        initComponents();
    }

    private void initComponents() {
        int buttonWidth = 200;
        int buttonHeight = 30;
        int verticalGap = 40;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        centerFrameOnScreen();
        setLayout(null);



        setBackground(new Color(34, 40, 49));


        addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds((getWidth() - buttonWidth) / 2, 30, buttonWidth, buttonHeight);
        setJButtonStyles(addPatientButton);
        setAddPatientButton(addPatientButton);
        add(addPatientButton);


        deletePatientButton = new JButton("Delete Patient");
        deletePatientButton.setBounds((getWidth() - buttonWidth) / 2, 30 + buttonHeight + verticalGap, buttonWidth, buttonHeight);
        setJButtonStyles(deletePatientButton);
        setDeletePatientButton(deletePatientButton);
        add(deletePatientButton);


        updatePatientButton = new JButton("Update Patient");
        updatePatientButton.setBounds((getWidth() - buttonWidth) / 2, 30 + 2 * (buttonHeight + verticalGap), buttonWidth, buttonHeight);
        setJButtonStyles(updatePatientButton);
        setUpdatePatientButton(updatePatientButton);
        add(updatePatientButton);


        bookingButton = new JButton("Book an appointment");
        bookingButton.setBounds((getWidth() - buttonWidth) / 2, 30 + 3 * (buttonHeight + verticalGap), buttonWidth, buttonHeight);
        setJButtonStyles(bookingButton);
        setBookingButton(bookingButton);
        add(bookingButton);


        cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.setBounds((getWidth() - buttonWidth) / 2, 30 + 4 * (buttonHeight + verticalGap), buttonWidth, buttonHeight);
        setJButtonStyles(cancelBookingButton);
        setCancelBookingButton(cancelBookingButton);
        add(cancelBookingButton);


        getContentPane().setBackground(new Color(34, 40, 49));



        centerFrameOnScreen();

        setVisible(true);
    }

    public void setJButtonStyles(JButton Button) {
        Button.setBackground(new Color(123, 168, 192));
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

    public JButton getAddPatientButton() {
        return addPatientButton;
    }

    public void setAddPatientButton(JButton addPatientButton) {
        this.addPatientButton = addPatientButton;
    }

    public JButton getDeletePatientButton() {
        return deletePatientButton;
    }

    public void setDeletePatientButton(JButton deletePatientButton) {
        this.deletePatientButton = deletePatientButton;
    }

    public JButton getUpdatePatientButton() {
        return updatePatientButton;
    }

    public void setUpdatePatientButton(JButton updatePatientButton) {
        this.updatePatientButton = updatePatientButton;
    }

    public JButton getBookingButton() {
        return bookingButton;
    }

    public void setBookingButton(JButton bookingButton) {
        this.bookingButton = bookingButton;
    }



    public void setCancelBookingButton(JButton cancelBookingButton) {
        this.cancelBookingButton = cancelBookingButton;
    }

    public JButton getCancelBookingButton() {
        return cancelBookingButton;
    }

    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

}
