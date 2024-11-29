package controllers;

import rminewclient.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class patientWindowController {
    private patientWindowForm patientWindowFormGui;

    public patientWindowController(patientWindowForm patientWindowFormGui) {
        this.patientWindowFormGui = patientWindowFormGui;
        attachEvents();
    }

    private void attachEvents() {
        patientWindowFormGui.getAddPatientButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patientWindowFormGui.dispose();


                addPatientForm addPatientFormGUI = new addPatientForm();
                new addPatientController(addPatientFormGUI);
            }
        });

        patientWindowFormGui.getDeletePatientButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patientWindowFormGui.dispose();


                deletePatientForm deletePatientFormGUI = new deletePatientForm();
                new deletePatientController(deletePatientFormGUI);
            }
        });

        patientWindowFormGui.getUpdatePatientButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patientWindowFormGui.dispose();


                updatePatientForm updatePatientFormGUI = new updatePatientForm();
                new updatePatientController(updatePatientFormGUI);
            }
        });

        patientWindowFormGui.getBookingButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patientWindowFormGui.dispose();


                bookAppointment bookAppointmentGUI = new bookAppointment();
                new bookAppointmentController(bookAppointmentGUI);
            }
        });

        patientWindowFormGui.getCancelBookingButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patientWindowFormGui.dispose();


                PatientLogInForm patientLogInFormGUI = new PatientLogInForm();
                new PatientLogInController(patientLogInFormGUI);
            }
        });

    }
}
