package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PaymentStrategy extends Remote {
    void MakePayment(int amount,String email) throws RemoteException;
    void RefundPayment(int amount,String email) throws RemoteException;
    String collectPaymentDetails() throws RemoteException;
    String getName()throws RemoteException;
}