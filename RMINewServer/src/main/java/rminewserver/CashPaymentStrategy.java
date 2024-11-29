package rminewserver;

import rmi.PaymentStrategy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CashPaymentStrategy extends UnicastRemoteObject implements PaymentStrategy {
    
    public CashPaymentStrategy() throws RemoteException {
        super();
    }

    @Override
    public void MakePayment(int amount,String email) throws RemoteException {
        System.out.println("Please go to the doctor clinic to  pay for your booking.");
    }

    @Override
    public void RefundPayment(int amount,String email) throws RemoteException {
        System.out.println("Please go to the doctor clinic to get your refund.");
    }

    @Override
    public String collectPaymentDetails() throws RemoteException {
        return "No payment details needed for cash payment.";
    }

    @Override
    public String getName() throws RemoteException {
        return "Cash";
    }
}