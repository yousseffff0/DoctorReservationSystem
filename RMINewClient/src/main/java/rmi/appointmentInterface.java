package rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface appointmentInterface extends Remote {

    public List<String> viewAvailableAppointments() throws RemoteException;
    public void addAppointment(String doctorName, String date, String time, String day) throws RemoteException;
    public void deleteAppointment(int appointmentId) throws RemoteException;
    public void updateAppointment(String name, String password, int appointmentId,int choice, String updatedvalue) throws RemoteException;
    public List<String> viewAppointment(String doctorName) throws RemoteException;

    }
