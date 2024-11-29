
package rminewserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.GradeInterface;

/**
 * This is just the implementation of our remote class
 * 
 */
public class Grade extends UnicastRemoteObject implements GradeInterface {
    
    public Grade() throws RemoteException {
        
    }
    
    @Override
    public String getGrade(int g) throws RemoteException {
        if (g >= 70) return "A";
        if (g >= 60) return "B";
        if (g >= 50) return "C";
        if (g >= 40) return "D";
        return "F";
    }
    
}
