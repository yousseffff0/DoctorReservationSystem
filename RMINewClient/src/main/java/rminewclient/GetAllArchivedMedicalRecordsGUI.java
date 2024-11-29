package rminewclient;

import javax.swing.*;
import java.awt.*;

public class GetAllArchivedMedicalRecordsGUI extends JFrame {
    private JTextArea recordsArea;
    private JButton closeButton;

    public GetAllArchivedMedicalRecordsGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        recordsArea = new JTextArea();
        recordsArea.setEditable(false);
        recordsArea.setForeground(Color.WHITE);
        recordsArea.setBackground(new Color(44, 62, 80));

        JScrollPane scrollPane = new JScrollPane(recordsArea);

        closeButton = new JButton("Close");

        // Set button background color and text color
        closeButton.setBackground(new Color(52, 152, 219));
        closeButton.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(44, 62, 80));
        add(panel);

        centerFrameOnScreen(this);

        setVisible(true);
    }

    public JTextArea getRecordsArea() {
        return recordsArea;
    }

    public JButton getCloseButton() {
        return closeButton;
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