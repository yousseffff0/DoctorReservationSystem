package rminewclient;

import javax.swing.*;
import java.awt.*;

public class UpdateMedicalRecordGUI extends JFrame {
    private JTextField nameField;
    private JTextField optionField;
    private JTextField updatedField;
    private JButton updateButton;

    public UpdateMedicalRecordGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        nameField = new JTextField();
        optionField = new JTextField();
        updatedField = new JTextField();
        updateButton = new JButton("Update Medical Record");

        updateButton.setBackground(new Color(52, 152, 219));
        updateButton.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 40, 49));
        panel.setLayout(new GridLayout(4, 2, 0, 15));
        panel.add(createWhiteLabel("Patient Name:"));
        panel.add(nameField);
        panel.add(createWhiteLabel("1 to update description or 2 to update diagnosis:"));
        panel.add(optionField);
        panel.add(createWhiteLabel("Updated Info:"));
        panel.add(updatedField);
        panel.add(new JLabel());
        panel.add(updateButton);

        getContentPane().setBackground(new Color(34, 40, 49));
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

    public JTextField getOptionField() {
        return optionField;
    }

    public JTextField getUpdatedField() {
        return updatedField;
    }

    public JButton getUpdateButton() {
        return updateButton;
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