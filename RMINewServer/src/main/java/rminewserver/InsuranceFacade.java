package rminewserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.InsuranceInterface;

public class InsuranceFacade extends UnicastRemoteObject implements InsuranceInterface {
    Insurance i;
    public InsuranceFacade()throws RemoteException {
        i= new Insurance("", 0, "");
    }

    @Override
    public void SetInsuranceData(String name, int amount, String insurancePolicyNumber)throws RemoteException {
        i.setAmount(amount);
        i.setInsurancePolicyNumber(insurancePolicyNumber);
        i.setName(name);
    }

    @Override
    public String getInsuranceData() throws RemoteException{
        String InsuranceData=i.getAmount()+i.getInsurancePolicyNumber()+i.getName();
        return InsuranceData;
    }

    @Override
    public InsuranceDTO getInsurance() throws RemoteException {
        
        InsuranceDTO dto=new InsuranceDTO(i.getName(), i.getInsurancePolicyNumber());
        return dto;
    }
}