package rminewclient;

import javax.swing.*;
import java.awt.*;

public class RemoveMedicalRecordGUI extends JFrame {
    private JTextField nameField;
    private JButton removeButton;

    public RemoveMedicalRecordGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        nameField = new JTextField();
        removeButton = new JButton("Remove Medical Record");

        removeButton.setBackground(new Color(52, 152, 219));
        removeButton.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setLayout(new GridLayout(2, 2, 0, 10));
        panel.add(createWhiteLabel("Patient Name:"));
        panel.add(nameField);
        panel.add(new JLabel());
        panel.add(removeButton);

        getContentPane().setBackground(new Color(44, 62, 80));
        add(panel);

        centerFrameOnScreen(this);

        setVisible(true);
    }

    private JLabel createWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        int frameX = centerX - frame.getWidth() / 2;
        int frameY = centerY - frame.getHeight() / 2;

        frame.setLocation(frameX, frameY);
    }
}