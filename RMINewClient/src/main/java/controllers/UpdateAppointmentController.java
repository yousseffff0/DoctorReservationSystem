package controllers;

import rmi.appointmentInterface;
import rminewclient.UpdateAppointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rminewclient.AppointmentGUI;

public class UpdateAppointmentController {
    private UpdateAppointment updateAppointment;
    private appointmentInterface appointmentInterface;
    private AppointmentGUI appointmentGUI;

    public UpdateAppointmentController(UpdateAppointment updateAppointment) {
        this.updateAppointment = updateAppointment;
         

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

 public void attachEvents() {
        updateAppointment.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = updateAppointment.getNameTextField().getText();
                String password = updateAppointment.getPasswordTextField().getText();
                int choice = (int) updateAppointment.getChoiceBox().getSelectedItem();

                try {

                    int appointmentId = Integer.parseInt(updateAppointment.getAppointmentIdTextField().getText());
                    
                    String valueToUpdate = updateAppointment.getValueToUpdateField().getText();

                    if (name.isEmpty() || password.isEmpty() || valueToUpdate.isEmpty()) {
                        JOptionPane.showMessageDialog(updateAppointment, "Please fill in all required fields.");
                    } else {
                        appointmentInterface.updateAppointment(name, password, appointmentId, choice, valueToUpdate);
                        System.out.println("Updating Appointment");
                        updateAppointment.dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(updateAppointment, "Invalid appointment ID. Please enter a valid number.");
                } catch (RemoteException ex) {
                    Logger.getLogger(UpdateAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}