package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MedicalRecordInterface extends Remote{
    public void AddMedicalRecord(String name,String desc,String diag) throws RemoteException;
    public void RemoveMedicalRecord(String name) throws RemoteException;
    public void UpdateMedicalRecord(String name,int option,String updated) throws RemoteException;
    public List<String> getAllMedicalRecords() throws RemoteException;
    public List<String> getAllArchivedMedicalRecord() throws RemoteException;
}