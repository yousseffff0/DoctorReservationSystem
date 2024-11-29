package rminewclient;

import javax.swing.*;
import java.awt.*;

public class AddMedicalRecordGUI extends JFrame {
    private JTextField nameField;
    private JTextField descField;
    private JTextField diagField;
    private JButton addButton;

    public AddMedicalRecordGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        nameField = new JTextField();
        descField = new JTextField();
        diagField = new JTextField();
        addButton = new JButton("Add Medical Record");

        addButton.setBackground(new Color(52, 152, 219));
        addButton.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 40, 49));
        panel.setLayout(new GridLayout(4, 2, 0, 10));
        
        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        panel.add(nameField);

        JLabel descLabel = new JLabel("Case Description:");
        descLabel.setForeground(Color.WHITE);
        panel.add(descLabel);

        panel.add(descField);

        JLabel diagLabel = new JLabel("Case Diagnosis:");
        diagLabel.setForeground(Color.WHITE);
        panel.add(diagLabel);

        panel.add(diagField);
        panel.add(new JLabel());

        addWhiteTextButton(addButton, "Add Medical Record");
        panel.add(addButton);

        getContentPane().setBackground(new Color(34, 40, 49));
        add(panel);

        centerFrameOnScreen(this);

        setVisible(true);
    }

    private void addWhiteTextButton(JButton button, String text) {
        button.setText(text);
        button.setForeground(Color.WHITE);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getDescField() {
        return descField;
    }

    public JTextField getDiagField() {
        return diagField;
    }

    public JButton getAddButton() {
        return addButton;
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