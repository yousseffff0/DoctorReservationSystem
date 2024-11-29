package rminewserver;

import java.rmi.RemoteException;
import java.util.Scanner;

public class Doctor extends Person {


    String Specialization;
    DB db = new DB();
    Appointment ap = new Appointment();

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }

    public Doctor(String Specialization, String name, String phoneNo, String email, String age, String password) throws RemoteException {
        super(name, phoneNo, email, age, password);
        this.Specialization = Specialization;
    }

    public Doctor() throws RemoteException {

    }

    public void AddDoctor() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter your name");
            String name = sc.nextLine();
            setName(name);

            System.out.println("Enter your email");
            String email = sc.nextLine();
            setEmail(email);

            System.out.println("Enter your phone number");
            String phoneNum = sc.nextLine();
            setPhoneNo(phoneNum);

            System.out.println("Enter your age");
            String age = sc.nextLine();
            setAge(age);

            System.out.println("Enter your password");
            String password = sc.nextLine();
            setPassword(password);

            System.out.println("Enter your specialization");
            String specialization = sc.nextLine();
            setSpecialization(specialization);

            Doctor doctor = new Doctor(getSpecialization(), getName(), getPhoneNo(), getEmail(), getAge(), getPassword());

//            db.insertDoctor(doctor);
        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
            e.printStackTrace();
        }
    }
//
//    public void ManageAppointment() throws RemoteException {
//
//        System.out.println("Enter 1 to add Appointment , 2 to delete Appointment , 3 to update Appointment  , 4 to view your Appointment");
//
//        Scanner doctorChoice = new Scanner(System.in);
//
//        try {
////            PrintPatientDetails();
//
//            int choice = doctorChoice.nextInt();
//
//
//            if (choice == 1) {
//                db.addAppointment();
//            } else if (choice == 2) {
//                db.deleteAppointment();
//            } else if (choice == 3) {
//                db.updateAppointment();
//            } else if (choice == 4) {
//                db.viewAppointment();
//            } else {
//                System.out.println("Wrong input");
//            }
//
//        } catch (java.util.InputMismatchException e) {
//            System.out.println("Invalid input. Please enter a valid integer.");
//        } finally {
//
//            doctorChoice.close();
//        }
//    }
}


