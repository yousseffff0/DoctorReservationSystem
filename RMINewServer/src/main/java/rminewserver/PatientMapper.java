package rminewserver;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.rmi.RemoteException;

import static rminewserver.DB.gson;

public class PatientMapper implements patientDataMapper {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> patientCollection;
    private final MongoCollection<Document> personCollection;

    public PatientMapper() throws RemoteException {
        this.mongoClient = MongoClients.create();
        this.database = mongoClient.getDatabase("DoctorReservationSystem");
        this.patientCollection = database.getCollection("patient");
        this.personCollection = database.getCollection("person");
    }



    @Override
    public void insertPatient(Patient patient) throws DataMapperException, RemoteException {
        Document lastPatient = patientCollection.find().sort(new Document("id", -1)).limit(1).first();
        int lastPatientId = (lastPatient != null) ? lastPatient.getInteger("id", 0) : 0;
        int newPatientId = lastPatientId + 1;

        patient.setId(newPatientId);
        patient.setPersonTypeID(1);

        Document patientDocument = new Document("id", newPatientId)
                .append("balance", patient.getBalance())
                .append("name", patient.getName())
                .append("phoneNo", patient.getPhoneNo())
                .append("email", patient.getEmail())
                .append("age", patient.getAge())
                .append("password", patient.getPassword())
                .append("personTypeID", patient.getPersonTypeID())
                .append("insuranceProviderName",patient.getInsuranceProviderName())
                .append("insurancePolicyNumber",patient.getInsurancePolicyNumber());

        patientCollection.insertOne(patientDocument);

        Person person = new Person(patient.getName(), patient.getPhoneNo(), patient.getEmail(), patient.getAge(), patient.getPassword());
        person.setId(newPatientId);
        person.setPersonTypeID(1);
        insertPerson(person);

        System.out.println("Patient and corresponding Person are inserted with ID: " + newPatientId + " and person type id:" + 1);
    }

    @Override
    public void insertPerson(Person person) throws DataMapperException, RemoteException {
        Document personDocument = new Document("id", person.getId())
                .append("name", person.getName())
                .append("phoneNo", person.getPhoneNo())
                .append("email", person.getEmail())
                .append("age", person.getAge())
                .append("password", person.getPassword())
                .append("personTypeID", person.getPersonTypeID());

        personCollection.insertOne(personDocument);
        System.out.println("Person is inserted with ID: " + person.getId() + " and person Type Id: " + person.getPersonTypeID());
    }



    @Override
    public void update(Patient patient) throws DataMapperException, RemoteException {
        Document query = new Document("_id", patient.getId());

        if (patientCollection.find(query).first() == null) {
            throw new DataMapperException("Patient with ID " + patient.getId() + " does not exist");
        }

        Document patientDocument = patientToDocument(patient);

        patientCollection.replaceOne(query, patientDocument);
    }

    @Override
    public void delete(int patientId) throws DataMapperException {
        Document query = new Document("id", patientId);

        if (patientCollection.find(query).first() == null) {
            throw new DataMapperException("Patient with ID " + patientId + " does not exist");
        }

        patientCollection.deleteOne(query);
    }


    private Document patientToDocument(Patient patient) throws RemoteException {
        return new Document("_id", patient.getId())
                .append("name", patient.getName())
                .append("phoneNo", patient.getPhoneNo())
                .append("email", patient.getEmail())
                .append("age", patient.getAge())
                .append("password", patient.getPassword())
                .append("balance", patient.getBalance())
                .append("insuranceProviderName", patient.getInsuranceProviderName())
                .append("insurancePolicyNumber", patient.getInsurancePolicyNumber());
    }

    private Patient documentToPatient(Document document) throws RemoteException {
        return new Patient(
                document.getString("name"),
                document.getString("phoneNo"),
                document.getString("email"),
                document.getString("age"),
                document.getString("password"),
                document.getInteger("balance"),
                document.getString("insuranceProviderName"),
                document.getString("insurancePolicyNumber")
        );
    }
}
