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


public class AddAppointmentController {
    private AddAppointment appointmentGUI;
    private appointmentInterface appointmentInterface;



    public AddAppointmentController(AddAppointment appointmentGUI){
        this.appointmentGUI = appointmentGUI;
        initializePatientInterface();
        attachEvents();
    }

    private void initializePatientInterface()  {
        try {
            Registry patientRegister = LocateRegistry.getRegistry(120);
            appointmentInterface = (appointmentInterface) patientRegister.lookup("appointment");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }


    public void attachEvents() {
    appointmentGUI.getAddButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String doctorName = appointmentGUI.getDoctorNameTextField().getText();
            String date = appointmentGUI.getDateTextField().getText();
            String time = appointmentGUI.getTimeTextField().getText();
            String day = appointmentGUI.getDayTextField().getText();

            if (doctorName.isEmpty() || date.isEmpty() || time.isEmpty() || day.isEmpty()) {
                JOptionPane.showMessageDialog(appointmentGUI, "Please fill in all required fields.");
            } else {
                try {
                    appointmentInterface.addAppointment(doctorName, date, time, day);
                    System.out.println("Adding Appointment");
                    appointmentGUI.dispose();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    });
}


}