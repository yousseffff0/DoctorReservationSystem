package controllers;

import rminewclient.RemoveMedicalRecordGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import rmi.MedicalRecordInterface;

public class RemoveMedicalRecordController {
    private RemoveMedicalRecordGUI gui;
    private MedicalRecordInterface medicalRecordInterface;

    public RemoveMedicalRecordController(RemoveMedicalRecordGUI gui) {
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
        gui.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = gui.getNameField().getText();

                try {
                    medicalRecordInterface.RemoveMedicalRecord(name);
                    System.out.println("remove Medical Record: " + name);
                    gui.dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}