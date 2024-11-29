package rminewserver;

import rmi.bookingInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;
import rmi.appointmentInterface;


public class Appointment extends UnicastRemoteObject implements appointmentInterface {

        private int AppointmentID;
        private boolean Available;
        private String Date;
        private String Time;
        private String Day;
        private String doctorName;
        private DB db = new DB();

    public Appointment(int AppointmentID, boolean Available, String Date, String Time, String Day, String doctorName) throws RemoteException {
        this.AppointmentID = AppointmentID;
        this.Available = Available;
        this.Date = Date;
        this.Time = Time;
        this.Day = Day;
        this.doctorName = doctorName;
    }
    public Appointment() throws RemoteException{

    };

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public int getAppointmentID() {
            return AppointmentID;
        }

        public void setAppointmentID(int AppointmentID) {
            this.AppointmentID = AppointmentID;
        }

        public boolean isAvailable() {
            return Available;
        }

        public void setAvailable(boolean Available) {
            this.Available = Available;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getDay() {
            return Day;
        }

        public void setDay(String Day) {
            this.Day = Day;
        }

//    public void PrintAppointmentDetails() {
//        Appointment appointment = db.getAppointmentById();
//        System.out.println("Appointment ID: " + appointment.getAppointmentID());
//        System.out.println("Available: " + appointment.isAvailable());
//        System.out.println("Date: " + appointment.getDate());
//        System.out.println("Time: " + appointment.getTime());
//        System.out.println("Day: " + appointment.getDay());
//        System.out.println("-----------------------------");
//    }

    public void printAllAvailableAppointments() {
        List<Appointment> availableAppointments = db.getAllAvailableAppointments();

        if (availableAppointments.isEmpty()) {
            System.out.println("No available appointments found.");
        } else {
            System.out.println("Available Appointments:");
            for (Appointment appointment : availableAppointments) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID());
                System.out.println("Available: " + appointment.isAvailable());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Time: " + appointment.getTime());
                System.out.println("Day: " + appointment.getDay());
                System.out.println("Doctor: " + appointment.getDoctorName());
                System.out.println("-----------------------------");
            }
        }
    }


    @Override
    public List<String> viewAvailableAppointments() throws RemoteException {
        return db.getAllAvailableAppointmentsRmi();
    }


    public void addAppointment() {
        DB db = new DB();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name");
        String doctorName = sc.nextLine();
        System.out.println("Enter your Password");
        String doctorPassword = sc.nextLine();

        if (db.isDoctor(doctorName, doctorPassword)) {
            System.out.println("Enter the Date");
            String date = sc.nextLine();
            System.out.println("Enter the Time");
            String time = sc.nextLine();
            System.out.println("Enter the Day");
            String day = sc.nextLine();


            this.setDate(date);
            this.setTime(time);
            this.setDay(day);
            this.setAvailable(true);
            this.setDoctorName(doctorName);


            db.addAppointmentToDatabase(this);
        } else {
            System.out.println("Access denied. Only doctors can add appointments.");
        }
    }

    public int getAppointmentHour(Appointment appointment) {
        String time = appointment.getTime();

        try {

            String[] timeParts = time.split(":");


            return Integer.parseInt(timeParts[0]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error parsing appointment time: " + e.getMessage());
            return -1;
        }
    }
    @Override
    public void addAppointment(String doctorName, String date, String time, String day) throws RemoteException {
        DB db = new DB();

        this.setDate(date);
        this.setTime(time);
        this.setDay(day);
        this.setAvailable(true);
        this.setDoctorName(doctorName);

        db.addAppointmentToDatabase(this);
    }

    @Override
    public void deleteAppointment( int appointmentId) throws RemoteException {
        DB db = new DB();

        // Assuming you have a method in your DB class to check doctor credentials and delete by appointmentId
        //if (db.checkDoctor(name, password)) {
        db.deleteAppointment( appointmentId);
    }
//else {
    //      System.out.println("Access denied. Invalid doctor credentials.");
    //}

    @Override
    public void updateAppointment(String name, String password,int appointmentId,int choice, String updatedvalue) throws RemoteException {
        System.out.println("DB CALL");
        db.updateAppointment(name, password,appointmentId, choice, updatedvalue);

    }
    @Override
    public List<String> viewAppointment(String doctorName)throws RemoteException {
        return db.viewAppointment(doctorName);
    }

}


