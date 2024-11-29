package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PaymentStrategy extends Remote {
    void MakePayment(int amount,String name) throws RemoteException;
    void RefundPayment(int amount,String name) throws RemoteException;
    String collectPaymentDetails() throws RemoteException;
    String getName()throws RemoteException;
}