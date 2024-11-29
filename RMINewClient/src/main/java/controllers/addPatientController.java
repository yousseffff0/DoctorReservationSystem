package controllers;


import rmi.patientInterface;
import rminewclient.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class addPatientController {
    private addPatientForm patientFormGui;
    private patientInterface patientInterface;



    public addPatientController(addPatientForm patientFormGui){
        this.patientFormGui = patientFormGui;
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

        patientFormGui.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = patientFormGui.getNameTextField().getText();
                String age = patientFormGui.getAgeTextField().getText();
                String phoneNumber = patientFormGui.getPhoneNumTextField().getText();
                String email = patientFormGui.getEmailTextField().getText();
                String password = patientFormGui.getPasswordTextField().getText();
                String balanceText = patientFormGui.getBalanceField().getText();
                String insuranceName = patientFormGui.getInsuranceNameField().getText();
                String insuranceNumber = patientFormGui.getInsuranceNumberField().getText();

                 if (name.isEmpty() || age.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() ||
                        password.isEmpty() || balanceText.isEmpty() ) {

                     JOptionPane.showMessageDialog(patientFormGui, "Please fill in all fields.");
                } else {
                    try {
                        // Proceed with adding the patient
                        int balance = Integer.parseInt(balanceText);
                        patientInterface.addPatient(name, phoneNumber, email, age, password, balance, insuranceName, insuranceNumber);
                        System.out.println("Adding Patient");
                        patientFormGui.dispose();
                    } catch (NumberFormatException ex) {
                        // Handle the case where the balance is not a valid integer
                        JOptionPane.showMessageDialog(patientFormGui, "Please enter a valid balance.");
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}
