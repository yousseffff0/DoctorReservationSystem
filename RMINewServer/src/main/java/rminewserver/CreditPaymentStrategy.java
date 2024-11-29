package rminewserver;

import rmi.PaymentStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Scanner;

public class CreditPaymentStrategy extends UnicastRemoteObject implements PaymentStrategy {
    private String cardNumber;
    private String email;
    private LocalDate expiryDate;
    private String cvv;
    private DB db = new DB();
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public CreditPaymentStrategy() throws RemoteException {
        super();
    }

    @Override
    public void MakePayment(int amount,String emaill) throws RemoteException {
        int balance = db.getBalanceByEmail(emaill);
        if (balance > amount) {
            db.subtractAmountToBalance(emaill, amount);
            System.out.println(amount + " EGP has been deducted from your balance.");
        }
    }

    @Override
    public void RefundPayment(int amount,String emaill) throws RemoteException {
        db.addAmountToBalance(emaill, amount);
        System.out.println(amount + " EGP has been added to your balance.");
    }

    @Override
    public String collectPaymentDetails() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Email address: ");
        email = scanner.nextLine().trim();

        System.out.print("Enter your 16-digit credit card number: ");
        cardNumber = scanner.nextLine().trim();

        System.out.print("Enter the 3-digit CVV code: ");
        cvv = scanner.nextLine().trim();

        System.out.print("Enter the expiry date in yyyy-mm-dd format: ");
        expiryDate = LocalDate.parse(scanner.nextLine().trim());

        return "Email: " + email + ", Card Number: " + cardNumber +
                ", CVV: " + cvv + ", Expiry Date: " + expiryDate;
    }

    @Override
    public String getName() throws RemoteException {
        return "cash";
    }
}