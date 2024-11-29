package controllers;

import rminewclient.FawryPaymentStrategyGUI;
import rmi.PaymentStrategy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FawryPaymentStrategyController {
    private FawryPaymentStrategyGUI gui;
    private PaymentStrategy fawryPayment;

    public FawryPaymentStrategyController(FawryPaymentStrategyGUI gui) {
        this.gui = gui;
        initializeCreditPaymentStrategy();
        attachEvents();
    }

    private void initializeCreditPaymentStrategy() {
        try {
            Registry registry = LocateRegistry.getRegistry(3000);
            fawryPayment = (PaymentStrategy) registry.lookup("FawryPayment");
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
                    String Number = gui.getNumber();
                    int amount = gui.getAmount();
                    String paymentDetails = "Email: " + email + ", Number: " + Number + ", Amount: " + amount;
                    gui.setPaymentResult("Fawry Payment: Making payment...\n" + paymentDetails);
                    fawryPayment.MakePayment(amount,email);
                    gui.setPaymentResult("Fawry Payment: Payment successful.\n" + paymentDetails);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    gui.setPaymentResult("Fawry Payment: Payment failed. " + ex.getMessage());
                }
            }
        });
    }
}