package rminewserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

import rmi.PaymentStrategy;
import rmi.bookingInterface;

public class Booking extends UnicastRemoteObject implements Subject, bookingInterface {

    private Appointment appointment = new Appointment();
    private static final int fixedBookingPrice = 1000;

    private int finalPrice;
    private int BookingId;
    private int patientId;
    private int Discount;

    private EmailSubscriber emailSubscriber = new EmailSubscriber();

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    DB db = new DB();

    public Booking(Appointment appointment, int price, int bookingId, int discount, int patientId) throws RemoteException {
        this.appointment = appointment;
        BookingId = bookingId;
        Discount = discount;
        this.patientId = patientId;
    }


    public Booking() throws RemoteException {

    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void printBookingDetails(int patientId) {
        DB db = new DB();
        List<Booking> bookingList = db.getBookingsByPatientId(patientId);
        for (Booking booking : bookingList) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Patient ID: " + booking.getPatientId());

            Appointment appointment = booking.getAppointment();
            if (appointment != null) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println("Day: " + appointment.getDay());
            }

            System.out.println("-----------------------");
        }
    }

    @Override
    public List<String> viewBookings(String name, String password) throws RemoteException{
        return db.getBookingsByUsernameAndPasswordRmi(name, password);
    }

    @Override
    public void bookAppointment(String name, String password, int appointmentId) throws RemoteException {
        try {
            Patient patient = new Patient();
            Booking booking = new Booking();
            //PaymentStrategy creditPayment = new PaymentStrategy();
            int patientId = 0;
            Insurance insurance = new Insurance();
//            appointment.printAllAvailableAppointments();
//            System.out.println("Enter the appointment id you want to book");
//            Scanner sc = new Scanner(System.in);
//            int appointmentId = sc.nextInt();
            appointment = db.getAppointmentById(appointmentId);
            if(appointment != null) {
                booking.setAppointment(appointment);
                System.out.println(appointment.getDay());
            }
            else {
                System.out.println("Appointment is null");
            }
            patient = db.getPatientByNameAndPassword(name, password);
            if (patient != null) {
                patientId = patient.getId();


            } else {
                System.out.println("null patient");
            }
            String email = patient.getEmail();
            System.out.println("email = "+ email);
            booking.setPatientId(patientId);
            int amount = db.getInsuranceAmount(patient);
            int bookingPrice = 0;
            booking.setFinalPrice(fixedBookingPrice - amount);
            bookingPrice = booking.getFinalPrice();
            db.changeAppointmentState(appointmentId);
            db.insertBooking(booking);
            //creditPayment.MakePayment(amount,email);
            notifyCustomer(appointment, bookingPrice, patientId, true);

        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void cancelBooking(String name, String password, int appointmentId) throws RemoteException {
        try {
        Patient patient = new Patient();
        Appointment appointment = new Appointment();
        patient = db.getPatientByNameAndPassword(name, password);
        int patientId = patient.getId();


//        System.out.println("Enter the appointment id that you want to delete : ");
//        Scanner sc = new Scanner(System.in);
//        int appointmentId = sc.nextInt();
        db.changeAppointmentState(appointmentId);
        db.removeBookingById(patientId);
        appointment = db.getAppointmentById(appointmentId);

        notifyCustomer(appointment, 0, patientId, false);


        } catch(
    RemoteException e)

    {
        System.err.println("RemoteException: " + e.getMessage());
        e.printStackTrace();
    }

}

    public void notifyCustomer(Appointment appointment, int price, int patientId, boolean isBooking) {
        if (appointment != null) {
            String doctor = appointment.getDoctorName();
            int time = appointment.getAppointmentHour(appointment);
            time = time - 12;
            System.out.println(time);
            String day = appointment.getDay();


            String action = isBooking ? "booked" : "canceled";
            String message = "Your appointment with doctor " + doctor + " in " + day + " at " +
                    time + " Pm has been " + action + ". ";

            if (isBooking) {
                message += "It will cost you " + price + " EGP ";
            } else {
                message += "No charges incurred.";
            }

            System.out.println("Sending message: " + message);
            updateAll(message, patientId);
        }
        else {
            System.out.println("null appointment");
        }
    }


    @Override
    public void subscribeToEmail(String name, String password) throws RemoteException {

        Patient patient = db.getPatientByNameAndPassword(name, password);
        String customerName = patient.getName();
        String customerEmail = patient.getEmail();
        int id = patient.getId();
        Observer o = emailSubscriber = new EmailSubscriber(customerName, customerEmail, id);

        addObserver(o);
    }


    @Override
    public void unSubscribeFromEmail(String name, String password) throws RemoteException {
        Patient patient = db.getPatientByNameAndPassword(name, password);
        String customerName = patient.getName();
        String customerEmail = patient.getEmail();
        int id = patient.getId();
        Observer o = emailSubscriber = new EmailSubscriber(customerName, customerEmail, id);

        removeObserver(o);
    }

    @Override
    public void addObserver(Observer o) {
        db.addEmailSubscriberToDatabase(o);
    }

    @Override
    public void removeObserver(Observer o) {
        String name = ((EmailSubscriber) o).getName();
        String email = ((EmailSubscriber) o).getEmail();
        db.removeEmailSubscriberFromDatabase(name, email);
    }

    @Override
    public void updateAll(String message, int patientId) {
        EmailSubscriber es = new EmailSubscriber();
        es.Update(message, patientId);
    }
}
