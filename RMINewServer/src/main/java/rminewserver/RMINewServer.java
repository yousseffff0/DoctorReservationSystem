/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rminewserver;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.*;
import rmi.feedbackInterface;
import rmi.FeedbackHandler;

public class RMINewServer {
    public static void main(String[] args) {
        try {
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);

            patientInterface pa = new Patient();
            bookingInterface b = new Booking();
            appointmentInterface ap = new Appointment();


            // An RMI Registry initialized on port 1099
            Registry patientRegister = LocateRegistry.createRegistry(100);
            Registry bookingRegister = LocateRegistry.createRegistry(110);
            Registry appointmentRegister = LocateRegistry.createRegistry(120);

            // Our remote object g is binded to the name "grade"
            patientRegister.bind("patient", pa);
            bookingRegister.bind("booking", b);
            appointmentRegister.bind("appointment", ap);
            // Instantiate payment strategy objects


            MedicalRecordInterface m = new MedicalRecord();
            PersonInterface p = new Person();
            CashPaymentStrategy cashPayment = new CashPaymentStrategy();
            CreditPaymentStrategy creditPayment = new CreditPaymentStrategy();
            FawryPaymentStrategy fawryPayment = new FawryPaymentStrategy();

            creditPayment.MakePayment(1000,"email");

            Registry registry3 = LocateRegistry.createRegistry(3000);

            // Export and bind payment strategy objects
            registry3.rebind("med", m);
            registry3.rebind("person", p);
            registry3.rebind("CashPayment", cashPayment);
            registry3.rebind("CreditPayment", creditPayment);
            registry3.rebind("FawryPayment", fawryPayment);

            System.out.println("My payment is ready...");
            System.out.println("My medical is ready...");

            feedbackInterface fd = new feedback();
            FeedbackHandler fbh =  new feedback();

            Registry feedback = LocateRegistry.createRegistry(1050);
            Registry feedbackHandler = LocateRegistry.createRegistry(1060);

            feedback.bind("feedback", fd);
            feedbackHandler.bind("feedbackHandler", fbh);

            System.out.println("The server is ready");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception: " + e.getMessage());
        }
    }
}


//
//public class RMINewServer {
//
//
//
//    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
//
//
//
//
//
//        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
//        mongoLogger.setLevel(Level.SEVERE);
//
//        PatientMapper patientMapper = new PatientMapper();
//
//        Patient patientToInsert = new Patient("ahmad", "012548422","ahmad@ayhaga.com","23","2345", 15000, "A", "f(4z8KF5Ih+c$RcT");
//
//
////        try {
////
////            patientMapper.delete(5);
////
////        } catch (DataMapperException e) {
////            e.printStackTrace();
////        }
//
//        DB db = new DB();
//
//        // Here we create our remote object

//
//
//        System.out.println("The server is ready");
//
//
//
////
//        Appointment appointment = new Appointment();
////        appointment.addAppointment();
////        Booking booking = new Booking();
////        Doctor doctor = new Doctor();
////        System.out.println(db.getBookingsByUsernameAndPasswordRmi("youssef", "youssef123"));
////        booking.bookAppointment("youssef", "youssef123", 4);
////        booking.cancelBooking();
////        System.out.println(db.getEmailById(8));
////        booking.unSubscribeFromEmail();
////        Scanner sc = new Scanner(System.in);
////        System.out.println("Enter your name");
////        String name = sc.nextLine();
////        System.out.println("Enter your password");
////        String password = sc.nextLine();
////        booking.subscribeToEmail(name, password);
//
//
////        booking.bookAppointment();
////        booking.subscribeToEmail();
////        db.printAllEmailSubscribers();
////        doctor.ManageAppointment();
//
////        doctor.ManageAppointment();
//
////        appointment.printAllAvailableAppointments();
////        booking.bookAppointment();
//
////        Insurance insurance = new Insurance();
////        insurance.addInsurance();
//
////        p1.PrintPatientDetails();
//
//
//        }
//
//
//
//
//
//}
