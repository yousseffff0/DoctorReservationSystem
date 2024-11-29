package rminewclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppointmentGUI extends JFrame {
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewButton;

    public AppointmentGUI() {
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

    
    addButton = new JButton("Add Appointment");
    addButton.setBounds(200, 50, 200, 40);
    setJButtonStyles(addButton);
    setAddButton(addButton);
    add(addButton);


    updateButton = new JButton("Update Appointment");
    updateButton.setBounds(200, 150, 200, 40);
    setJButtonStyles(updateButton);
    setUpdateButton(updateButton);
    add(updateButton);

   
    deleteButton = new JButton("Delete Appointment");
    deleteButton.setBounds(200, 250, 200, 40);
    setJButtonStyles(deleteButton);
    setDeleteButton(deleteButton);
    add(deleteButton);
    
    viewButton = new JButton("View Appointment");
    viewButton.setBounds(200, 350, 200, 40);
    setJButtonStyles(viewButton);
    setViewButton(viewButton);
    add(viewButton);

    getContentPane().setBackground(new Color(34, 40, 49));


    setVisible(true);
}
    
    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
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


public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    

   

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public void setViewButton(JButton viewButton) {
        this.viewButton = viewButton;
    }
    

}

