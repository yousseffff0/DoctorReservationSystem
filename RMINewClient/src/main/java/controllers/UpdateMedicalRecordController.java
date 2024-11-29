package controllers;

import rminewclient.UpdateMedicalRecordGUI;
import rmi.MedicalRecordInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UpdateMedicalRecordController {
    private UpdateMedicalRecordGUI gui;
    private MedicalRecordInterface medicalRecordInterface;

    public UpdateMedicalRecordController(UpdateMedicalRecordGUI gui) {
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
        gui.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = gui.getNameField().getText();
                int option = Integer.parseInt(gui.getOptionField().getText());
                String updated = gui.getUpdatedField().getText();

                try {
                    medicalRecordInterface.UpdateMedicalRecord(name, option, updated);
                    System.out.println("Updating Medical Record: " + name + ", Option: " + option + ", Updated: " + updated);
                    gui.dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}