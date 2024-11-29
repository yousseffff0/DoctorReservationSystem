package rminewserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.PersonInterface;

public class Person extends UnicastRemoteObject implements PersonInterface{
    private String name;
    private String phoneNo;
    private String Email;
    private String age;
    private String password;
    public int id ;
    private int personTypeID;

    private DB db = new DB();

    public Person() throws RemoteException{
    }


    public Person(String name, String phoneNo, String Email, String age, String password) throws RemoteException{
        this.name = name;
        this.phoneNo = phoneNo;
        this.Email = Email;
        this.age = age;
        this.password = password;
    }

    public String getName() throws RemoteException{
        return name;
    }

    public void setName(String name) throws RemoteException{
        this.name = name;
    }

    public String getPhoneNo() throws RemoteException{
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) throws RemoteException{
        this.phoneNo = phoneNo;
    }

    public String getEmail() throws RemoteException{
        return Email;
    }

    public void setEmail(String Email) throws RemoteException{
        this.Email = Email;
    }

    public String getAge() throws RemoteException{
        return age;
    }

    public void setAge(String age) throws RemoteException{
        this.age = age;
    }

    public String getPassword() throws RemoteException{
        return password;
    }

    public void setPassword(String password) throws RemoteException{
        this.password = password;
    }

    public int getId() throws RemoteException{
        return id;
    }

    public void setId(int id) throws RemoteException{
        this.id = id;
    }

    public int getPersonTypeID() throws RemoteException{
        return personTypeID;
    }

    public void setPersonTypeID(int personTypeID) throws RemoteException{
        this.personTypeID = personTypeID;
    }

    @Override
    public int getPersonTypeId(String emaill, String passwordd) throws RemoteException{
        System.out.println(emaill+passwordd);
        int personTypeId = db.getPersonTypeId(emaill, passwordd);
        System.out.println(personTypeId);
        return personTypeId;
    }
}