/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rminewclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import controllers.*;
import rmi.*;

import javax.swing.*;


public class RMINewClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //MedicalRecordGUI medicalRecordGUI = new MedicalRecordGUI();
            //new MedicalRecordController(medicalRecordGUI);
            //PaymentStrategyGUI paymentStrategyGUI = new PaymentStrategyGUI();
            //new PaymentStrategyController(paymentStrategyGUI);
            //LoginGUI loginGUI = new LoginGUI();
            //new LoginController(loginGUI);
            patientWindowForm patientWindowFormGUI = new patientWindowForm();
            new patientWindowController(patientWindowFormGUI);

//                  SwingUtilities.invokeLater(() -> {
//            FeedbackGUI feedbackGUI = new FeedbackGUI();
//            new controller.FeedbackController(feedbackGUI);
        });


//            SwingUtilities.invokeLater(() -> {
//                AppointmentGUI appointmentGUI = new AppointmentGUI();
//                new AppointmentController(appointmentGUI);
//            });
    }

}


//    public static void main(String[] args)  {
//        SwingUtilities.invokeLater(() -> {
//
//        });
//
//    }

//}



//        // We create an object from the GUI window
//        addPatientForm gui = new addPatientForm();
//        gui.setLocationRelativeTo(null); // This makes the window appears centered
//        gui.setVisible(true); // This shows the gui
//
//        // We connect to the RMI Registry
//        Registry patientRegister = LocateRegistry.getRegistry(100);
//        patientInterface patientInterface = (patientInterface) patientRegister.lookup("patient");
//        Registry bookingRegister = LocateRegistry.getRegistry(110);
//        bookingInterface bookingInterface = (bookingInterface) bookingRegister.lookup("booking");
//
//        Registry appointmentRegister = LocateRegistry.getRegistry(120);
//        appointmentInterface appointmentInterface = (appointmentInterface) appointmentRegister.lookup("appointment") ;
//        Scanner scanner = new Scanner(System.in);
//
//        Scanner scannerChoice = new Scanner(System.in);
//
////        List<String> appointments = appointmentInterface.viewAvailableAppointments();
////        for (String appointmentsDetails: appointments)
////            System.out.println(appointmentsDetails);
//
////        bookingInterface.bookAppointment("youssef", "youssef123", 4);
//
//        List<String> bookings = bookingInterface.viewBookings(8);
//        for (String bookingDetails: bookings)
//            System.out.println(bookingDetails);
//
//
////        System.out.println("Enter Name:");
////        String name = scanner.nextLine();
////
////        System.out.println("Enter email:");
////        String email = scanner.nextLine();
////
////        System.out.println("Enter phone number:");
////        String phoneNum = scanner.nextLine();
////
////        System.out.println("Enter age:");
////        String age = scanner.nextLine();
////
////        System.out.println("Enter password:");
////        String password = scanner.nextLine();
////
////        System.out.println("Enter balance:");
////        int balance = scanner.nextInt();
////
////
////        scanner.nextLine();
////
////
////        System.out.println("Enter insurance provider patientName:");
////        String insuranceProviderName = scanner.nextLine();
////
////        System.out.println("Enter insurance policy number:");
////        String insurancePolicyNumber = scanner.nextLine();
////
////        patientInterface.addPatient(name, phoneNum, email, age, password, balance, insuranceProviderName, insurancePolicyNumber);
////
////
////
////
////
////        System.out.println("Enter your Name:");
////        String patientName = scanner.nextLine();
////
////        System.out.println("Enter your password:");
////        String patientPassword = scanner.nextLine();
//
////
////        boolean isUser = patientInterface.checkUser(patientName, patientPassword);
////
////        System.out.println("Select operation:");
////        System.out.println("1. Delete Patient");
////        System.out.println("2. update Patient details");
////        int choice = scannerChoice.nextInt();
////
////
////        switch (choice){
////            case 1:
////                if (isUser) {
////
////                    patientInterface.deletePatient(patientName, patientPassword);
////                }
////                else {
////                    System.out.println("User name and password do not match:");
////                    break;
////                }
////                break;
////
////            case 2:
////                if (isUser) {
////                    String patientData = " ";
////                    patientData = patientInterface.retrievePatientDetails(patientName, patientPassword);
////                    System.out.println(patientData);
////                    String valueToUpdate = "";
////
////                    System.out.println("Choose what to update:");
////                    System.out.println("1. Update phone number");
////                    System.out.println("2. Update email");
////                    System.out.println("3. Update balance");
////                    System.out.println("4. Update insurancePolicyName");
////                    System.out.println("5. Update insurancePolicyNumber");
////                    int updateChoice = scanner.nextInt();
////                    scanner.nextLine();
////
////                    System.out.println("Enter your updated info : ");
////                    valueToUpdate = scanner.nextLine();
////                    patientInterface.updatePatientInfo(patientName, patientPassword, updateChoice, valueToUpdate);
////                }
////                else {
////                    System.out.println("User name and password do not match:");
////                    break;
////                }
////
////        }
////
//
//
//
////        // we create a new object from the controller ,and we pass it the
////        // gui object along with the registry object
////        MainWindowController gui_controller = new MainWindowController(gui, r);
//

    

