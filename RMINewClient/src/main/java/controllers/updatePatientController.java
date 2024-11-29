package controllers;

import rmi.patientInterface;
import rminewclient.updatePatientForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class updatePatientController {
    private updatePatientForm updatePatientGui;
    private patientInterface patientInterface;

    public updatePatientController(updatePatientForm updatePatientGui) {
        this.updatePatientGui = updatePatientGui;
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
        updatePatientGui.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = updatePatientGui.getNameTextField().getText();
                String password = updatePatientGui.getPasswordTextField().getText();
                int choice = (int) updatePatientGui.getChoiceBox().getSelectedItem();
                String valueToUpdate = updatePatientGui.getValueToUpdateField().getText();

                 if (name.isEmpty() || password.isEmpty() || valueToUpdate.isEmpty()) {

                     JOptionPane.showMessageDialog(updatePatientGui, "Please fill in all required fields.");
                } else {
                    try {
                         patientInterface.updatePatientInfo(name, password, choice, valueToUpdate);
                        System.out.println("Updating Patient");
                        updatePatientGui.dispose();
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}
