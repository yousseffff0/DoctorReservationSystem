/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author omar mohamed
 */
public interface feedbackInterface extends Remote {
    public void addFeedback(String doctorName, int feedbackRate, String description, String feedbackStatus) throws RemoteException;
    public List<String> feedbackCall(int choice) throws RemoteException;
    public List<String> showFeedback() throws RemoteException;
}
