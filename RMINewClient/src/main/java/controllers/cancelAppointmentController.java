package controllers;

import rmi.appointmentInterface;
import rmi.bookingInterface;
import rminewclient.cancelAppointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

public class cancelAppointmentController {
    private cancelAppointment cancelAppointmentGUI;
    private bookingInterface bookingInterface;
    private appointmentInterface appointmentInterface;
    private Map<String, String> userCredentials;


    public cancelAppointmentController(cancelAppointment cancelAppointmentGUI, Map<String, String> userCredentials) {
        this.cancelAppointmentGUI = cancelAppointmentGUI;
        this.userCredentials = userCredentials;
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

            String username = userCredentials.get("name");
            String password = userCredentials.get("password");

            if (username != null && password != null) {
                List<String> bookedAppointments = bookingInterface.viewBookings(username, password);
                updateRecordsTextArea(bookedAppointments);
            } else {
                System.out.println("User credentials not available.");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        cancelAppointmentGUI.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userCredentials.get("name");
                String password = userCredentials.get("password");
                int id = Integer.parseInt(cancelAppointmentGUI.getAppointmentIdField().getText());

                try {
                    bookingInterface.cancelBooking(name, password, id);
                    System.out.println("Canceling Appointment");
                    cancelAppointmentGUI.dispose();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void updateRecordsTextArea(List<String> availableAppointments) {
        StringBuilder recordsText = new StringBuilder();

        for (String record : availableAppointments) {
            recordsText.append(record).append("\n");
        }

        cancelAppointmentGUI.getRecordsArea().setText(recordsText.toString());
    }
}
