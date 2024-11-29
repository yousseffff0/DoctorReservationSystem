package rminewserver;

import java.util.Scanner;

public class Insurance {

    private String name;
    private int Id;
    private int amount;
    private String insurancePolicyNumber;

    public Insurance(String name, int amount, String insurancePolicyNumber) {
        this.name = name;
        this.amount = amount;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }


    public Insurance() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }
    
    public void addInsurance() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.println("Enter Insurance company name : ");
        String name = sc.nextLine();
        System.out.println("Enter Insurance policy number: ");
        String policyNumber = sc.nextLine();
        System.out.println("Enter Insurance amount : ");
        int amount = sc2.nextInt();
        setName(name);
        setAmount(amount);
        Insurance insurance = new Insurance(name, amount,policyNumber);
    }

    public void manageInsurance() {
        System.out.println("Enter 1 to add insurance , 2 to delete the insurance");

        Scanner patientChoice = new Scanner(System.in);

        try {

            int choice = patientChoice.nextInt();

            if (choice == 1) {
                addInsurance();
            } else if (choice == 2) {
            } else {
                System.out.println("Wrong input");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } finally {

            patientChoice.close();

        }
    }

}