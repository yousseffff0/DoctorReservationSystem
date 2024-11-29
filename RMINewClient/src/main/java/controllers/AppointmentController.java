package controllers;

import rminewclient.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppointmentController {
    private AppointmentGUI appointmentGUI;
    private AddAppointment addAppointment;
    private DeleteAppointment deleteAppointment;
    private ViewAppointment viewAppointment;
    private UpdateAppointment updateAppointment;

    public AppointmentController(AppointmentGUI appointmentGUI) {
        this.appointmentGUI = appointmentGUI;
        attachEvents();
    }

    private void attachEvents() {
        appointmentGUI.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appointmentGUI.dispose();
                AddAppointment addAppointmentFormGUI = new AddAppointment();
                new AddAppointmentController(addAppointmentFormGUI);
            }
        });

        appointmentGUI.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appointmentGUI.dispose();
                DeleteAppointment deleteAppointmentFormGUI = new DeleteAppointment();
                new DeleteAppointmentController(deleteAppointmentFormGUI);
            }
        });

        appointmentGUI.getViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appointmentGUI.dispose();
                ViewAppointment viewAppointmentFormGUI = new ViewAppointment();
                new ViewAppointmentController(viewAppointmentFormGUI);
            }
        });

        appointmentGUI.getUpdateButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        appointmentGUI.dispose();
        UpdateAppointment updateAppointmentFormGUI = new UpdateAppointment();
        new UpdateAppointmentController(updateAppointmentFormGUI);
    }
});

    }
}



//
//    }
