package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface bookingInterface extends Remote {

    public void bookAppointment(String name, String password, int appointmentId) throws RemoteException ;
    public List<String> viewBookings(String name, String password) throws RemoteException;

    public void cancelBooking(String name, String password, int appointmentId) throws RemoteException ;

    public void subscribeToEmail(String name, String password) throws RemoteException;

    public void unSubscribeFromEmail(String name, String password) throws RemoteException;




}
