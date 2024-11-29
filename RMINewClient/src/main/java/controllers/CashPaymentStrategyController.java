package controllers;

import rminewclient.CashPaymentStrategyGUI;
import rmi.PaymentStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CashPaymentStrategyController {
    private CashPaymentStrategyGUI gui;
    private PaymentStrategy cashPayment;

    public CashPaymentStrategyController(CashPaymentStrategyGUI gui) {
        this.gui = gui;
        initializeCashPaymentStrategy();
        attachEvents();
    }

    private void initializeCashPaymentStrategy() {
        try {
            Registry registry = LocateRegistry.getRegistry(3000);
            cashPayment = (PaymentStrategy) registry.lookup("CashPayment");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void attachEvents() {
        gui.getMakePaymentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gui.appendToOutputArea("Cash Payment: Please go to the doctor clinic to get for your booking.");
                    cashPayment.MakePayment(1000,"youssef207845@bue.edu.eg");
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}