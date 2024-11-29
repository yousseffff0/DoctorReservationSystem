package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface PersonInterface  extends Remote{
    public int getPersonTypeId(String email, String password)throws RemoteException;
}