package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rminewclient.MedicalRecordGUI;
import rminewclient.AddMedicalRecordGUI;
import rminewclient.GetAllArchivedMedicalRecordsGUI;
import rminewclient.GetAllMedicalRecordsGUI;
import rminewclient.RemoveMedicalRecordGUI;
import rminewclient.UpdateMedicalRecordGUI;

public class MedicalRecordController {
    private MedicalRecordGUI medicalRecordGUI;

    public MedicalRecordController(MedicalRecordGUI medicalRecordGUI) {
        this.medicalRecordGUI = medicalRecordGUI;
        attachEvents();
    }

    private void attachEvents() {
        medicalRecordGUI.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicalRecordGUI.dispose();
                AddMedicalRecordGUI addMedicalRecordGUI = new AddMedicalRecordGUI();
                new AddMedicalRecordController(addMedicalRecordGUI);
            }
        });

        medicalRecordGUI.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicalRecordGUI.dispose();
                RemoveMedicalRecordGUI RemoveMedicalRecordGUI = new RemoveMedicalRecordGUI();
                new RemoveMedicalRecordController(RemoveMedicalRecordGUI);
            }
        });

        medicalRecordGUI.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicalRecordGUI.dispose();
                UpdateMedicalRecordGUI updateMedicalRecordGUI = new UpdateMedicalRecordGUI();
                new UpdateMedicalRecordController(updateMedicalRecordGUI);

            }
        });

        medicalRecordGUI.getGetAllButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicalRecordGUI.dispose();
                GetAllMedicalRecordsGUI GetAllMedicalRecordsGUI = new GetAllMedicalRecordsGUI();
                new GetAllMedicalRecordsController(GetAllMedicalRecordsGUI);
            }
        });

        medicalRecordGUI.getGetArchivedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicalRecordGUI.dispose();
                GetAllArchivedMedicalRecordsGUI GetAllArchivedMedicalRecordsGUI = new GetAllArchivedMedicalRecordsGUI();
                new GetAllArchivedMedicalRecordsController(GetAllArchivedMedicalRecordsGUI);
            }
        });
    }
}