package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rminewclient.CashPaymentStrategyGUI;
import rminewclient.CreditPaymentStrategyGUI;
import rminewclient.FawryPaymentStrategyGUI;
import rminewclient.PaymentStrategyGUI;


public class PaymentStrategyController {
    private PaymentStrategyGUI PaymentStrategyGUI;

    public PaymentStrategyController(PaymentStrategyGUI PaymentStrategyGUI) {
        this.PaymentStrategyGUI = PaymentStrategyGUI;
        attachEvents();
    }

    private void attachEvents() {
        PaymentStrategyGUI.getCashButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentStrategyGUI.dispose();
                CashPaymentStrategyGUI CashPaymentStrategyGUI = new CashPaymentStrategyGUI();
                new CashPaymentStrategyController(CashPaymentStrategyGUI);
            }
        });

        PaymentStrategyGUI.getCreditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentStrategyGUI.dispose();
                CreditPaymentStrategyGUI CreditPaymentStrategyGUI = new CreditPaymentStrategyGUI();
                new CreditPaymentStrategyController(CreditPaymentStrategyGUI);
            }
        });

        PaymentStrategyGUI.getFawryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentStrategyGUI.dispose();
                FawryPaymentStrategyGUI FawryPaymentStrategyGUI = new FawryPaymentStrategyGUI();
                new FawryPaymentStrategyController(FawryPaymentStrategyGUI);

            }
        });
    }
}