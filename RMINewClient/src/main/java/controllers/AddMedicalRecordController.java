package controllers;

import rminewclient.AddMedicalRecordGUI;
import rmi.MedicalRecordInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AddMedicalRecordController {
    private AddMedicalRecordGUI gui;
    private MedicalRecordInterface medicalRecordInterface; 

    public AddMedicalRecordController(AddMedicalRecordGUI gui) {
        this.gui = gui;
        initializeMedicalRecordInterface(); 
        attachEvents();
    }

    private void initializeMedicalRecordInterface() {
        try {
            Registry registry = LocateRegistry.getRegistry(3000);
            medicalRecordInterface = (MedicalRecordInterface) registry.lookup("med");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void attachEvents() {
        gui.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = gui.getNameField().getText();
                String desc = gui.getDescField().getText();
                String diag = gui.getDiagField().getText();
                try {
                    medicalRecordInterface.AddMedicalRecord(name, desc, diag);
                    System.out.println("Adding Medical Record: " + name + ", " + desc + ", " + diag);
                    gui.dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}