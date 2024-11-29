package rminewserver;

import java.rmi.RemoteException;
import java.util.Optional;

public interface patientDataMapper {




    void insertPatient(Patient patient) throws DataMapperException, RemoteException;

    void insertPerson(Person person) throws DataMapperException, RemoteException;

    void update(Patient patient) throws DataMapperException, RemoteException;

    void delete(int patientId) throws DataMapperException;
}
