package rminewserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import rmi.MedicalRecordInterface;

public class MedicalRecord extends UnicastRemoteObject implements MedicalRecordInterface{
    private int PatientId;
    private int MedicalRecordId;
    private String PatientName;
    private String Description;
    private String Diagnosis;
    private boolean Completed;
    private DB db = new DB();

    public MedicalRecord(int PatientId,String PatientName, String Description, String Diagnosis, boolean Completed) throws RemoteException{
        this.PatientId = PatientId;
        this.PatientName = PatientName;
        this.Description = Description;
        this.Diagnosis = Diagnosis;
        this.Completed = Completed;
    }

    public MedicalRecord() throws RemoteException{
    }

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int patientId) {
        this.PatientId = patientId;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        this.PatientName = patientName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    public int getMedicalRecordId() {
        return MedicalRecordId;
    }

    public void setMedicalRecordId(int MedicalRecordId) {
        this.MedicalRecordId = MedicalRecordId;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean Completed) {
        this.Completed = Completed;
    }
    
    @Override
    public void AddMedicalRecord(String name,String desc,String diag)throws RemoteException{
        int PID=db.getPersonIdByName(name);
        boolean state=false;
        MedicalRecord m = new MedicalRecord(PID,name,desc,diag,state);
        db.addMedicalRecord(m);
    }
    
    @Override
    public void RemoveMedicalRecord(String name)throws RemoteException{
        db.archiveMedicalRecord(name);
    }
    
    @Override
    public void UpdateMedicalRecord(String name,int option,String updated)throws RemoteException{
        db.updateMedicalRecord(name, option, updated);
    }
    
    @Override
    public List<String> getAllMedicalRecords() throws RemoteException {
    return db.getAllMedicalRecords();
    }

    @Override
    public List<String> getAllArchivedMedicalRecord() throws RemoteException {
    return db.getAllArchivedMedicalRecord();
    }
}