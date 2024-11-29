package rmi;

import rminewserver.InsuranceDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InsuranceInterface extends Remote {
    public void SetInsuranceData(String name, int amount, String insurancePolicyNumber)throws RemoteException;
    public String getInsuranceData()throws RemoteException;
    public InsuranceDTO getInsurance() throws RemoteException;
}