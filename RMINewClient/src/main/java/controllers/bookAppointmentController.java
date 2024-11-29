package controllers;

import rminewclient.bookAppointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import rmi.*;
import rminewclient.cancelAppointment;
import rminewclient.subscribeToEmailForm;

import javax.swing.*;

public class bookAppointmentController {
    private bookAppointment bookAppointmentGUI;
    private bookingInterface bookingInterface;
    private appointmentInterface appointmentInterface;

    public bookAppointmentController(bookAppointment bookAppointmentGUI) {
        this.bookAppointmentGUI = bookAppointmentGUI;
        initializeBookingInterface();
        attachEvents();
    }

    private void initializeBookingInterface() {
        try {
            Registry bookingRegister = LocateRegistry.getRegistry(110);
            bookingInterface = (bookingInterface) bookingRegister.lookup("booking");

            Registry appointmentRegister = LocateRegistry.getRegistry(120);
            appointmentInterface = (appointmentInterface) appointmentRegister.lookup("appointment");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();

        }
    }

    private void attachEvents() {
        try {
            List<String> availableAppointments = appointmentInterface.viewAvailableAppointments();
            System.out.println("found");
            updateRecordsTextArea(availableAppointments);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        bookAppointmentGUI.getBookButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = bookAppointmentGUI.getNameTextField().getText();
                String password = bookAppointmentGUI.getPasswordTextField().getText();
                String appointmentIdText = bookAppointmentGUI.getAppointmentIdField().getText();

                if (name.isEmpty() || password.isEmpty() || appointmentIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(bookAppointmentGUI, "Please fill in all required fields.");
                } else {
                    try {
                        int id = Integer.parseInt(appointmentIdText);
                        bookingInterface.bookAppointment(name, password, id);
                        System.out.println("Booking Appointment");
                        bookAppointmentGUI.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(bookAppointmentGUI, "Please enter a valid appointment ID.");
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        bookAppointmentGUI.getSubscribeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subscribeToEmailForm subscribeToEmailGui = new subscribeToEmailForm();
                new subscribeToEmailController(subscribeToEmailGui);
                bookAppointmentGUI.dispose();
            }
        });
    }



    private void updateRecordsTextArea(List<String> availableAppointments) {
        StringBuilder recordsText = new StringBuilder();

        for (String record : availableAppointments) {
            recordsText.append(record).append("\n");
//            System.out.println("Adding data");
        }

        bookAppointmentGUI.getRecordsArea().setText(recordsText.toString());
    }
}
