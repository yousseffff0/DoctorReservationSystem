package controllers;

import rminewclient.CreditPaymentStrategyGUI;
import rmi.PaymentStrategy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CreditPaymentStrategyController {
    private CreditPaymentStrategyGUI gui;
    private PaymentStrategy creditPayment;

    public CreditPaymentStrategyController(CreditPaymentStrategyGUI gui) {
        this.gui = gui;
        initializeCreditPaymentStrategy();
        attachEvents();
    }

    private void initializeCreditPaymentStrategy() {
        try {
            Registry registry = LocateRegistry.getRegistry(3000);
            creditPayment = (PaymentStrategy) registry.lookup("CreditPayment");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void attachEvents() {
        gui.getMakePaymentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = gui.getEmail();
                    String cardNumber = gui.getCardNumber();
                    String cvv = gui.getCVV();
                    String expiryDate = gui.getExpiryDate();
                    int amount = gui.getAmount();
                    String paymentDetails = "Email: " + email + ", Card Number: " + cardNumber +
                    ", CVV: " + cvv + ", Expiry Date: " + expiryDate +", Amount: " + amount;
                    gui.setPaymentResult("Credit Payment: Making payment...\n" + paymentDetails);
                    creditPayment.MakePayment(amount,email);
                    gui.setPaymentResult("Credit Payment: Payment successful.\n" + paymentDetails);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    gui.setPaymentResult("Credit Payment: Payment failed. " + ex.getMessage());
                }
            }
        });
    }
}