package controllers;

import rminewclient.GetAllArchivedMedicalRecordsGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import rmi.MedicalRecordInterface;

public class GetAllArchivedMedicalRecordsController {
    private GetAllArchivedMedicalRecordsGUI gui;
    private MedicalRecordInterface medicalRecordInterface;

    public GetAllArchivedMedicalRecordsController(GetAllArchivedMedicalRecordsGUI gui) {
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
        try {
            List<String> archivedmedicalRecords = medicalRecordInterface.getAllArchivedMedicalRecord();
            updateRecordsTextArea(archivedmedicalRecords);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        gui.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.dispose();
            }
        });
    }

    private void updateRecordsTextArea(List<String> medicalRecords) {
        StringBuilder recordsText = new StringBuilder();
        for (String record : medicalRecords) {
            recordsText.append(record).append("\n");
        }
        gui.getRecordsArea().setText(recordsText.toString());
    }
}