package rminewclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashPaymentStrategyGUI extends JFrame {
    private JTextArea outputTextArea;
    private JButton makePaymentButton;

    public CashPaymentStrategyGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setForeground(Color.WHITE);
        outputTextArea.setBackground(new Color(44, 62, 80));

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        makePaymentButton = new JButton("Make Payment");
        makePaymentButton.setBackground(new Color(52, 152, 219));
        makePaymentButton.setForeground(Color.WHITE);

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(makePaymentButton, BorderLayout.SOUTH);
        panel.setBackground(new Color(44, 62, 80)); // Dark background

        getContentPane().add(panel);

        centerFrameOnScreen(this);

        setVisible(true);
    }

    public void appendToOutputArea(String text) {
        outputTextArea.append(text + "\n");
    }

    public JButton getMakePaymentButton() {
        return makePaymentButton;
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