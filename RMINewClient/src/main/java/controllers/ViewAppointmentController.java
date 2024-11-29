package controllers;

import rmi.*;
import rminewclient.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.JOptionPane;

public class ViewAppointmentController {
    private ViewAppointment gui;
    private appointmentInterface appointmentInterface;

    public ViewAppointmentController(ViewAppointment gui) {
        this.gui = gui;
        initializeAppointmentInterface();
        attachEvents();
    }

    private void initializeAppointmentInterface() {
        try {
            Registry registry = LocateRegistry.getRegistry(120);
            appointmentInterface = (appointmentInterface) registry.lookup("appointment");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
         }
    }

    private void attachEvents() {
    gui.getViewButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String doctorName = gui.getNameTextField().getText();

            try {
                if (doctorName.isEmpty()) {
                    JOptionPane.showMessageDialog(gui, "Please enter a doctor name.");
                } else {
                    List<String> appointments = appointmentInterface.viewAppointment(doctorName);
                     updateRecordsTextArea(appointments);
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
             }
        }
    });

    
}


    private void updateRecordsTextArea(List<String> appointments) {
        StringBuilder recordsText = new StringBuilder();

        for (String record : appointments) {
            recordsText.append(record).append("\n");
        }

        gui.getRecordsArea().setText(recordsText.toString());
    }
}
