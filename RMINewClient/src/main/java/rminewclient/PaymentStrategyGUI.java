package rminewclient;

import javax.swing.*;
import java.awt.*;

public class PaymentStrategyGUI extends JFrame {
    private JButton cashButton;
    private JButton creditButton;
    private JButton fawryButton;

    public PaymentStrategyGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 300);

        cashButton = createStyledButton("Pay With Cash");
        creditButton = createStyledButton("Pay With Credit");
        fawryButton = createStyledButton("Pay With Fawry");
        

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 40, 49));
        panel.setLayout(new GridLayout(3, 1, 0, 10));
        panel.add(cashButton);
        panel.add(creditButton);
        panel.add(fawryButton);

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

    public JButton getCashButton() {
        return cashButton;
    }

    public JButton getCreditButton() {
        return creditButton;
    }

    public JButton getFawryButton() {
        return fawryButton;
    }
}