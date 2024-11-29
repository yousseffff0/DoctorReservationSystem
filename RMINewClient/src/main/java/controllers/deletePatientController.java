package controllers;

import rmi.patientInterface;
import rminewclient.deletePatientForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class deletePatientController {
    private deletePatientForm deletePatientGui;
    private rmi.patientInterface patientInterface;

    public deletePatientController(deletePatientForm deletePatientGui) {
        this.deletePatientGui = deletePatientGui;
        initializePatientInterface();
        attachEvents();
    }

    private void initializePatientInterface()  {
        try {
            Registry patientRegister = LocateRegistry.getRegistry(100);
            patientInterface = (patientInterface) patientRegister.lookup("patient");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void attachEvents() {
        deletePatientGui.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = deletePatientGui.getNameTextField().getText();
                String password = deletePatientGui.getPasswordTextField().getText();


                if (name.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(deletePatientGui, "Please enter both name and password.");
                } else {
                    try {
                        // Proceed with the deletion
                        patientInterface.deletePatient(name, password);
                        System.out.println("Deleting Patient");
                        deletePatientGui.dispose();
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}
