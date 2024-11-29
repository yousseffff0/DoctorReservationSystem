package controllers;

import rmi.bookingInterface;
import rminewclient.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class subscribeToEmailController {

    subscribeToEmailForm subscribeToEmailGui;
    private bookingInterface bookingInterface;


    public subscribeToEmailController(subscribeToEmailForm subscribeToEmailGui) {
        this.subscribeToEmailGui = subscribeToEmailGui;
        initializeBookingInterface();
        attachEvents();

    }

    private void initializeBookingInterface() {
        try {
            Registry bookingRegister = LocateRegistry.getRegistry(110);
            bookingInterface = (bookingInterface) bookingRegister.lookup("booking");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();

        }
    }

    private void attachEvents() {
        subscribeToEmailGui.getSubscriptionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = subscribeToEmailGui.getNameTextField().getText();
                String password = subscribeToEmailGui.getPasswordTextField().getText();

                if (name.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(subscribeToEmailGui, "Please fill in all required fields.");
                } else {
                    try {
                        bookingInterface.subscribeToEmail(name, password);
                        System.out.println("Subscribing to Email ");
                        subscribeToEmailGui.dispose();
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        subscribeToEmailGui.getUnSubscribeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = subscribeToEmailGui.getNameTextField().getText();
                String password = subscribeToEmailGui.getPasswordTextField().getText();

                if (name.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(subscribeToEmailGui, "Please fill in all required fields.");
                } else {
                    try {
                        bookingInterface.unSubscribeFromEmail(name, password);
                        System.out.println("Unsubscribe from Email ");
                        subscribeToEmailGui.dispose();
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


    }

}
