package rminewserver;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import rmi.patientInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

public class Patient extends Person implements patientInterface , Serializable {


    private int balance;
    private String insuranceProviderName;
    private String insurancePolicyNumber;

    private  DB db = new DB();

    public Patient(String name, String phoneNo, String email, String age, String password, int balance, String insuranceProviderName, String insurancePolicyNumber) throws RemoteException {
        super(name, phoneNo, email, age, password);
        this.balance = balance;
        this.insuranceProviderName = insuranceProviderName;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getInsuranceProviderName() {
        return insuranceProviderName;
    }

    public void setInsuranceProviderName(String insuranceProviderName) {
        this.insuranceProviderName = insuranceProviderName;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public Patient() throws RemoteException {

    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void addPatient(String name, String email, String phoneNum, String age, String password, int balance, String insuranceProviderName, String insurancePolicyNumber) throws RemoteException {

        try {


//     a  =    "f(4z8KF5Ih+c$RcT"

//      b =     YAPf7#EMx6dk%h!w


//      c =      g!&STE3#GaPk8qHr


            Patient patient = new Patient(name, email, phoneNum, age, password, balance, insuranceProviderName, insurancePolicyNumber);

            db.insertPatient(patient);
        }catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void PrintPatientDetails(String name, String password) throws RemoteException {
        Patient patient = db.getPatientByNameAndPassword(name, password);
        System.out.println("Patient ID: " + patient.getId());
        System.out.println("Name: " + patient.getName());
        System.out.println("Phone Number: " + patient.getPhoneNo());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Insurance provider name: " + patient.getInsuranceProviderName());
    }

//    public void ManageAccount() {
//
//        System.out.println("Enter 1 to update your account, 2 to delete your account");
//
//        Scanner patientChoice = new Scanner(System.in);
//
//        try {
////            PrintPatientDetails();
//
//            int choice = patientChoice.nextInt();
//
//
//            if (choice == 1) {
//                db.updatePatientInfo();
//            } else if (choice == 2) {
////                db.removePatient();
//            } else {
//                System.out.println("Wrong input");
//            }
//
//        } catch (java.util.InputMismatchException e) {
//            System.out.println("Invalid input. Please enter a valid integer.");
//        } finally {
//
//            patientChoice.close();
//        }
//    }

    @Override
    public boolean checkUser(String patientName, String patientPassword){
        if (db.checkPatient(patientName, patientPassword))
            return true;
        else
            return false;

    }

    @Override
    public String retrievePatientDetails(String name, String password) {
            String patient = db.getPatientDataByNameAndPassword(name, password);
            return patient;
    }

    @Override
    public void deletePatient(String name, String password){
        Scanner sc = new Scanner(System.in);

         db.removePatient(name, password);
    }

    @Override
    public void updatePatientInfo(String name,  String password, int choice ,String valueToUpdate){
        db.updatePatientInfo(name, password, choice,valueToUpdate);
    }

    @Override
    public String toString() {
        try {
            return "Patient{" +
                    "name='" + getName() + '\'' +
                    ", phoneNo='" + getPhoneNo() + '\'' +
                    ", email='" + getEmail() + '\'' +
                    ", age='" + getAge() + '\'' +
                    ", password='" + getPassword() + '\'' +
                    ", balance=" + balance +
                    ", insuranceProviderName='" + insuranceProviderName + '\'' +
                    ", insurancePolicyNumber='" + insurancePolicyNumber + '\'' +
                    '}';
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


}