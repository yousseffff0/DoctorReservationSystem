package rminewserver;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.PaymentStrategy;

public class Payment extends UnicastRemoteObject{
    private  int Paymentid;
    private PaymentStrategy paymentStrategy;

    public Payment() throws RemoteException {
        super();
    }

    public int getPaymentid() {
        return Paymentid;
    }

    public void setpaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void MakePayment(int amount,String emaill) throws RemoteException {
        System.out.println("Making payment of " + amount + " EGP using " + paymentStrategy.getName() + "...");
        paymentStrategy.MakePayment(amount,emaill);
    }

    public void RefundPayment(int amount,String emaill) throws RemoteException {
        System.out.println("Making refund of " + amount + " EGP using " + paymentStrategy.getName() + "...");
        paymentStrategy.RefundPayment(amount,emaill);
    }
}



