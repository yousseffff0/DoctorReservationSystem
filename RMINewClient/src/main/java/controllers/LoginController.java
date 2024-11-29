package controllers;
import rminewclient.LoginGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.PersonInterface;
import rminewclient.AddMedicalRecordGUI;
import rminewclient.patientWindowForm;

public class LoginController {
    private LoginGUI loginGUI;
    private PersonInterface personInterface;
    int personTypeId;

    public LoginController(LoginGUI loginGUI) {
        this.loginGUI = loginGUI;
        initializePersonInterface();
        attachEvents();
    }
    
    private void initializePersonInterface() {
        try {
            Registry registry = LocateRegistry.getRegistry(3000);
            personInterface = (PersonInterface) registry.lookup("person");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void attachEvents() {
        loginGUI.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform login validation
                String email = loginGUI.getEmail();
                String password = loginGUI.getPassword();
                try {
                     personTypeId = personInterface.getPersonTypeId(email, password);
                } catch (RemoteException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                // For simplicity, let's assume valid email and password
                switch (personTypeId) {
                    case 1:
                        JOptionPane.showMessageDialog(loginGUI, "Navigating to Patient Page");
                        loginGUI.dispose();
                        patientWindowForm patientWindowFormGUI = new patientWindowForm();
                        new patientWindowController(patientWindowFormGUI);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(loginGUI, "Navigating to Doctor Page");
                        showMessage("Navigating to Doctor Page");
                        // Add your navigation logic here
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(loginGUI, "Navigating to Admin Page");
                        showMessage("Navigating to Admin Page");
                        // Add your navigation logic here
                        break;
                    default:
                        JOptionPane.showMessageDialog(loginGUI, "Invalid Email or Password");
                        break;
                }
            }
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(loginGUI, message);
    }
}