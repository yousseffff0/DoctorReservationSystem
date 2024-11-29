package rminewclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditPaymentStrategyGUI extends JFrame {
    private JTextField emailField;
    private JTextField cardNumberField;
    private JTextField cvvField;
    private JTextField expiryDateField;
    private JTextField amountField;
    private JButton makePaymentButton;
    private JTextArea outputTextArea;

    public CreditPaymentStrategyGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        emailField = new JTextField();
        cardNumberField = new JTextField();
        cvvField = new JTextField();
        expiryDateField = new JTextField();
        amountField = new JTextField();
        makePaymentButton = new JButton("Make Payment");
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setForeground(Color.WHITE);
        outputTextArea.setBackground(new Color(44, 62, 80));

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 0, 10));
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Card Number:"));
        inputPanel.add(cardNumberField);
        inputPanel.add(new JLabel("CVV:"));
        inputPanel.add(cvvField);
        inputPanel.add(new JLabel("Expiry Date:"));
        inputPanel.add(expiryDateField);
        inputPanel.add(new JLabel("amount :"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel());
        inputPanel.add(makePaymentButton);

        makePaymentButton.setBackground(new Color(52, 152, 219));
        makePaymentButton.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(new Color(44, 62, 80));

        getContentPane().add(panel);

        centerFrameOnScreen(this);

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        setVisible(true);
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getCardNumber() {
        return cardNumberField.getText().trim();
    }

    public String getCVV() {
        return cvvField.getText().trim();
    }

    public String getExpiryDate() {
        return expiryDateField.getText().trim();
    }
    
    public int getAmount() {
        return Integer.parseInt(amountField.getText().trim());
    }
    
    public JButton getMakePaymentButton() {
        return makePaymentButton;
    }

    public void setPaymentResult(String result) {
        outputTextArea.setText(result);
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