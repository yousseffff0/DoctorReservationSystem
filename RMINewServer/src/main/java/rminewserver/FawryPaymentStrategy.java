package rminewserver;

import rmi.PaymentStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class FawryPaymentStrategy extends UnicastRemoteObject implements PaymentStrategy {
    private String number;
    private String email;
    private DB db = new DB();
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public FawryPaymentStrategy() throws RemoteException {
        super();
    }

    @Override
    public void MakePayment(int amount,String emaill) throws RemoteException {
        int balance = db.getBalanceByEmail(emaill);
        if (balance > amount) {
            db.subtractAmountToBalance(emaill, amount);
            System.out.println(amount + " EGP has been deducted from your number : " + number);
        }
    }

    @Override
    public void RefundPayment(int amount,String emaill) throws RemoteException {
        db.addAmountToBalance(emaill, amount);
        System.out.println(amount + " EGP has been added to your Number : " + number);
    }

    @Override
    public String collectPaymentDetails() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Fawry number: ");
        number = scanner.nextLine().trim();

        System.out.print("Enter your email: ");
        email = scanner.nextLine().trim();

        return "Fawry Number: " + number + ", Email: " + email;
    }

    @Override
    public String getName() throws RemoteException {
        return "Fawry";
    }
}