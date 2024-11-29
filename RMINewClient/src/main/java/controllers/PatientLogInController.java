package controllers;

import rmi.patientInterface;
import rminewclient.PatientLogInForm;
import rminewclient.bookAppointment;
import rminewclient.cancelAppointment;
import rminewclient.updatePatientForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class PatientLogInController {
    private PatientLogInForm patientLogInFormGui;
    private patientInterface patientInterface;
    private Map<String, String> userCredentials;

    public PatientLogInController(PatientLogInForm patientLogInFormGui) {
        this.patientLogInFormGui = patientLogInFormGui;
        this.userCredentials = new HashMap<>();
        initializePatientInterface();
        attachEvents();
    }

    private void initializePatientInterface() {
        try {
            Registry patientRegister = LocateRegistry.getRegistry(100);
            patientInterface = (patientInterface) patientRegister.lookup("patient");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void attachEvents() {
        patientLogInFormGui.getLogInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = patientLogInFormGui.getNameTextField().getText();
                String password = patientLogInFormGui.getPasswordTextField().getText();

                // Check if both name and password are provided
                if (name.isEmpty() || password.isEmpty()) {
                    // Display a message or handle the case where either field is empty
                    JOptionPane.showMessageDialog(patientLogInFormGui, "Please enter both name and password.");
                } else {
                    try {
                        if (patientInterface.checkUser(name, password)) {
                            System.out.println("Logging in Patient");

                            userCredentials.put("name", name);
                            userCredentials.put("password", password);

                            cancelAppointment cancelAppointmentGUI = new cancelAppointment();
                            cancelAppointmentGUI.setPatientCredentials(name, password);
                            new cancelAppointmentController(cancelAppointmentGUI, userCredentials);
                            patientLogInFormGui.dispose();
                        } else {
                            // Handle the case where the login credentials are incorrect
                            JOptionPane.showMessageDialog(patientLogInFormGui, "Invalid login credentials.");
                        }
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}
