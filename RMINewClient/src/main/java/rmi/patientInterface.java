package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface patientInterface extends Remote {

    public boolean checkUser(String name, String password) throws RemoteException;

    public void addPatient(String name, String email, String phoneNum, String age, String password, int balance, String insuranceProviderName, String insurancePolicyNumber) throws RemoteException;

    public void deletePatient(String name,  String password) throws RemoteException;

    public String retrievePatientDetails(String name,  String password) throws RemoteException;

    public void updatePatientInfo(String name, String password, int choice, String valueToUpdate) throws RemoteException;


}
