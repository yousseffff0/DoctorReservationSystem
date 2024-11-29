package rminewclient;

import javax.swing.*;
import java.awt.*;

public class MedicalRecordGUI extends JFrame {
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton getAllButton;
    private JButton getArchivedButton;

    public MedicalRecordGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        addButton = createStyledButton("Add Medical Record");
        removeButton = createStyledButton("Remove Medical Record");
        updateButton = createStyledButton("Update Medical Record");
        getAllButton = createStyledButton("Get All Medical Record");
        getArchivedButton = createStyledButton("Get Archived Medical Record");
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 40, 49));
        panel.setLayout(new GridLayout(5, 1, 0, 10));
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(updateButton);
        panel.add(getAllButton);
        panel.add(getArchivedButton);
        
        getContentPane().setBackground(new Color(34, 40, 49));
        add(panel);
        
        centerFrameOnScreen(this);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 30));
        return button;
    }

    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        int frameX = centerX - frame.getWidth() / 2;
        int frameY = centerY - frame.getHeight() / 2;

        frame.setLocation(frameX, frameY);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getGetAllButton() {
        return getAllButton;
    }

    public JButton getGetArchivedButton() {
        return getArchivedButton;
    }
}