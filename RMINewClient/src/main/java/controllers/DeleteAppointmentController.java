package controllers;


import rmi.appointmentInterface;
import rminewclient.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;


public class DeleteAppointmentController {
    
    private AppointmentGUI appointmentGUI;
    private DeleteAppointment deleteAppointment;
    private appointmentInterface appointmentInterface;



    public DeleteAppointmentController(DeleteAppointment deleteAppointment){
        

        this.deleteAppointment = deleteAppointment;
        initializeAppointmentInterface();
        attachEvents();
    }

    private void initializeAppointmentInterface()  {
        try {
            Registry Register = LocateRegistry.getRegistry(120);
            appointmentInterface = (appointmentInterface) Register.lookup("appointment");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

public void attachEvents() {
    deleteAppointment.getDeleteButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                 int appointmentId = Integer.parseInt(deleteAppointment.getAppointmentIdTextField().getText());

                if (deleteAppointment.getAppointmentIdTextField().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(deleteAppointment, "Please enter an appointment ID.");
                } else {
                    appointmentInterface.deleteAppointment(appointmentId);
                    System.out.println("Appointment Deleted");
                    deleteAppointment.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(deleteAppointment, "Invalid appointment ID. Please enter a valid number.");
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
}


}