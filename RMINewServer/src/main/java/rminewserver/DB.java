package rminewserver;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.MongoException;
import com.mongodb.client.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;


public class DB {
    public static MongoClient mongoClient;
    public static MongoDatabase database;
    MongoCollection<Document> personCollection;
    MongoCollection<Document> patientCollection;
    MongoCollection<Document> appointmentCollection;
    MongoCollection<Document> doctorCollection;
    MongoCollection<Document> bookingCollection;
    MongoCollection<Document> insuranceCollection;
    MongoCollection<Document> emailSubscriberCollection;
    MongoCollection<Document> feedbackCollection;
    MongoCollection<Document> adminCollection;
    MongoCollection<Document>  MedicalRecordCollection;
    MongoCollection<Document> ArchivedMedicalRecordCollection;


    public static Gson gson = new Gson();

    public DB() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        mongoClient = MongoClients.create("mongodb://localhost:27017");


        database = mongoClient.getDatabase("DoctorReservationSystem");


        personCollection = database.getCollection("person");
        patientCollection = database.getCollection("patient");

        appointmentCollection = database.getCollection("Appointment");
        doctorCollection = database.getCollection("doctor");
        bookingCollection = database.getCollection("booking");
        insuranceCollection = database.getCollection("Insurance");
        emailSubscriberCollection = database.getCollection("email subscriber");
        feedbackCollection = database.getCollection("feedback");
        adminCollection = database.getCollection("admin");
        MedicalRecordCollection = database.getCollection("MedicalRecord");
        ArchivedMedicalRecordCollection = database.getCollection("ArchivedMedicalRecord");


    }


    public void insertPerson(Person person) {
        try {
            Document personDocument = new Document("id", person.getId())
                    .append("personTypeID", person.getPersonTypeID())
                    .append("name", person.getName())
                    .append("phoneNo", person.getPhoneNo())
                    .append("email", person.getEmail())
                    .append("age", person.getAge())
                    .append("password", person.getPassword());

            personCollection.insertOne(personDocument);

            System.out.println("Person is inserted with ID: " + person.getId() + " and person Type Id: " + person.getPersonTypeID());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


//    Youssef Ahmad


    public void insertPatient(Patient patient) throws RemoteException {
        try {
            Document lastPatient = patientCollection.find().sort(new Document("id", -1)).limit(1).first();

            int lastPatientId = (lastPatient != null) ? lastPatient.getInteger("id", 0) : 0;
            int newPatientId = lastPatientId + 1;

            patient.setId(newPatientId);
            patient.setPersonTypeID(1);

            Document patientDocument = new Document("id", newPatientId)
                    .append("personTypeID", 1)
                    .append("name", patient.getName())
                    .append("phoneNo", patient.getPhoneNo())
                    .append("email", patient.getEmail())
                    .append("age", patient.getAge())
                    .append("password", patient.getPassword())
                    .append("balance", patient.getBalance())
                    .append("insuranceProviderName",patient.getInsuranceProviderName())
                    .append("insurancePolicyNumber",patient.getInsurancePolicyNumber());

            patientCollection.insertOne(patientDocument);


            Person person = new Person(patient.getName(), patient.getPhoneNo(), patient.getEmail(), patient.getAge(), patient.getPassword());
            person.setId(newPatientId);
            person.setPersonTypeID(1);
            insertPerson(person);

            System.out.println("Doctor and corresponding Person are inserted with ID: " + newPatientId + " and person type id:" + 1);

        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private FindIterable<Document> findPatientByNameAndPassword(String name, String password) {
        try {
            return patientCollection.find(
                    new Document("name", name)
                            .append("password", password)
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public Patient getPatientByNameAndPassword(String name, String password) {
        try {


            FindIterable<Document> result = findPatientByNameAndPassword(name, password);

            MongoCursor<Document> cursor = result.iterator();
            if (cursor.hasNext()) {
                Document patientDoc = cursor.next();
                System.out.println("Found user in the database:\n" + patientDoc.toJson());

                try {

                    int balance = patientDoc.getInteger("balance");
                    String phoneNo = patientDoc.getString("phoneNo");
                    String email = patientDoc.getString("email");
                    String age = patientDoc.getString("age");
                    String insuranceProviderName = patientDoc.getString("insuranceProviderName");
                    String insurancePolicyNumber = patientDoc.getString("insurancePolicyNumber");
                    int id = patientDoc.getInteger("id");
                    System.out.println(id);



                    Patient patient = new Patient(name, phoneNo, email, age, password, balance, insuranceProviderName, insurancePolicyNumber);
                    patient.setId(id);

                    System.out.println("Manually constructed Patient object:\n" + patient);
                    return patient;
                } catch (Exception e) {
                    System.out.println("Error during manual construction: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            } else {
                System.out.println("No user found with the given name and password.");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: Please enter a valid value.");
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public boolean checkPatient(String name, String password) {

            FindIterable<Document> result = findPatientByNameAndPassword(name, password);

            MongoCursor<Document> cursor = result.iterator();
            if (cursor.hasNext()) {
                Document patientDoc = cursor.next();
                System.out.println("user founded");
                return true;
            } else {
                System.out.println("No user found with the given name and password.");
                return false;
            }

    }

    public String getPatientDataByNameAndPassword(String name, String password) {
        try {

            FindIterable<Document> result = findPatientByNameAndPassword(name, password);

            MongoCursor<Document> cursor = result.iterator();
            if (cursor.hasNext()) {
                Document patientDoc = cursor.next();
                return patientDoc.toJson();
            } else {
                System.out.println("No user found with the given name and password.");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: Please enter a valid value.");
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String getEmailById(int userId) {
        try {
            FindIterable<Document> result = emailSubscriberCollection.find(eq("id", userId));
            MongoCursor<Document> cursor = result.iterator();

            if (cursor.hasNext()) {
                Document emailSubscriberDoc = cursor.next();
                return emailSubscriberDoc.getString("email");
            } else {
                System.out.println("No email subscriber found with the given user ID.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void removeBookingById(int patientId) {
        try {
            bookingCollection.deleteOne(Filters.eq("patientId", patientId));
            System.out.println("Booking with ID " + patientId + " removed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public List<Booking> getBookingsByPatientId(int patientId) {
        List<Booking> bookings = new ArrayList<>();

        try {

            FindIterable<Document> result = bookingCollection.find(Filters.eq("patientId", patientId));

            for (Document bookingDoc : result) {
                Booking booking = new Booking();


                booking.setBookingId(bookingDoc.getInteger("bookingId"));
                booking.setPatientId(bookingDoc.getInteger("patientId"));


                Document appointmentDoc = bookingDoc.get("appointment", Document.class);
                if (appointmentDoc != null) {
                    Appointment appointment = new Appointment();


                    appointment.setAppointmentID(appointmentDoc.getInteger("appointmentId"));
                    appointment.setDate(appointmentDoc.getString("date"));
                    appointment.setTime(appointmentDoc.getString("time"));
                    appointment.setDay(appointmentDoc.getString("day"));



                    booking.setAppointment(appointment);
                }


                bookings.add(booking);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return bookings;
    }

    public List<String> getBookingsByPatientIdRmi(int patientId) {
        List<String> bookingDetails = new ArrayList<>();

        try {
            FindIterable<Document> result = bookingCollection.find(Filters.eq("patientId", patientId));

            for (Document bookingDoc : result) {
                StringBuilder bookingInfo = new StringBuilder();

                bookingInfo.append("BookingID: ").append(bookingDoc.getInteger("bookingId")).append(", ");
                bookingInfo.append("PatientID: ").append(bookingDoc.getInteger("patientId")).append(", ");

                Document appointmentDoc = bookingDoc.get("appointment", Document.class);
                if (appointmentDoc != null) {
                    bookingInfo.append("AppointmentID: ").append(appointmentDoc.getInteger("appointmentId")).append(", ");
                    bookingInfo.append("Date: ").append(appointmentDoc.getString("date")).append(", ");
                    bookingInfo.append("Time: ").append(appointmentDoc.getString("time")).append(", ");
                    bookingInfo.append("Day: ").append(appointmentDoc.getString("day"));
                }

                bookingDetails.add(bookingInfo.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return bookingDetails;
    }








    public void removeEmailSubscriberFromDatabase(String name, String email) {
        try {

            emailSubscriberCollection.deleteOne(Filters.and(
                    Filters.eq("name", name),
                    Filters.eq("email", email)
            ));

            System.out.println("Subscriber removed from the database.");

        } catch (Exception e) {
            System.err.println("DATABASE DELETION ERROR: " + e.toString());
        }
    }

    private FindIterable<Document> findAppointmentById(int id) {
        try {

            return appointmentCollection.find(
                    new Document("AppointmentID", id)
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void changeAppointmentState(int id) {
        try {
            FindIterable<Document> result = findAppointmentById(id);

            MongoCursor<Document> cursor = (result != null) ? result.iterator() : null;
            if (cursor != null && cursor.hasNext()) {
                Document appointmentDoc = cursor.next();


                boolean currentState = appointmentDoc.getBoolean("Available", true);
                appointmentDoc.put("Available", !currentState);

                appointmentCollection.updateOne(
                        new Document("AppointmentID", id),
                        new Document("$set", appointmentDoc)
                );

                System.out.println("Appointment state changed successfully.");
            } else {
                System.out.println("No appointment found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean isAppointmentAvailable(int appointmentId) {
        try {
            Document appointmentDoc = appointmentCollection.find(Filters.eq("_id", appointmentId)).first();

            if (appointmentDoc != null) {
                // Assuming 'available' is the field representing availability in the document
                return !appointmentDoc.getBoolean("Available", false);
            } else {
                return false; // Appointment not found
            }
        } catch (Exception e) {
            // Handle exceptions as needed
            return false;
        }
    }

    public void insertBooking(Booking booking) {
        try {

            Document lastBooking = bookingCollection.find().sort(new Document("bookingId", -1)).limit(1).first();

            int lastBookingId = (lastBooking != null) ? lastBooking.getInteger("bookingId", 0) : 0;
            int newBookingId = lastBookingId + 1;

            booking.setBookingId(newBookingId);


            Document bookingDoc = new Document("bookingId", booking.getBookingId())
                    .append("appointment", new Document("AppointmentId", booking.getAppointment().getAppointmentID())
                            .append("Date", booking.getAppointment().getDate())
                            .append("Time", booking.getAppointment().getTime())
                            .append("Day", booking.getAppointment().getDay())
                            .append("doctorName", booking.getAppointment().getDoctorName()))
                    .append("Patient Id", booking.getPatientId());


            bookingCollection.insertOne(bookingDoc);

            System.out.println("Booking added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBooking(int bookingId) {
        try {

            bookingCollection.deleteOne(Filters.eq("bookingId", bookingId));

            System.out.println("Booking deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Patient getPatientFromBooking(String username, String password) {
        try {

            Bson filter = Filters.and(
                    Filters.eq("patient.name", username),
                    Filters.eq("patient.password", password)
            );


            Document bookingDoc = bookingCollection.find(filter).first();

            if (bookingDoc != null) {

                Document patientDoc = (Document) bookingDoc.get("patient");


                Patient patient = new Patient();
                patient.setName(patientDoc.getString("name"));
                patient.setEmail(patientDoc.getString("email"));
                patient.setId(patientDoc.getInteger("patientId"));

                return patient;
            } else {
                System.out.println("Patient with username " + username + " and password not found in bookings.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void insertInsurance(Insurance insurance) {

        Document lastInsurance = insuranceCollection.find().sort(new Document("AppointmentID", -1)).limit(1).first();

        int lastInsuranceId = (lastInsurance != null) ? lastInsurance.getInteger("AppointmentID", 0) : 0;
        int newInsuranceId = lastInsuranceId + 1;

        insurance.setId(newInsuranceId);


        Document insuranceDocument = new Document("insuranceId", newInsuranceId)
                .append("name", insurance.getName())
                .append("amount", insurance.getAmount());


        insuranceCollection.insertOne(insuranceDocument);


        mongoClient.close();
    }

    public boolean checkInsuranceInDatabase() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter insurance company name: ");
        String name = sc.nextLine();

        Document insurance = insuranceCollection.find(Filters.eq("name", name)).first();

        return insurance != null;
    }

    public void removeInsurance() {
        try {
            System.out.println("Enter the insurance name");
            Scanner sc = new Scanner(System.in);
            String insuranceName = sc.nextLine();

            insuranceCollection.deleteOne(Filters.eq("name", insuranceName));

            System.out.println("Insurance removed from the database.");
        } catch (Exception e) {
            System.err.println("An error occurred while removing the insurance: " + e.getMessage());
        } finally {

            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }


    public int getInsuranceAmount(Patient patient) {

        String policyNumber = patient.getInsurancePolicyNumber();
        try {
            Document insurance = insuranceCollection.find(Filters.eq("insurancePolicyNumber", policyNumber)).first();

            if (insurance != null) {
                return insurance.getInteger("amount", -1);
            }

            if (patient != null) {

                String insuranceName = patient.getInsuranceProviderName();
                Document insuranceDoc = insuranceCollection.find(Filters.eq("name", insuranceName)).first();

                return (insuranceDoc != null) ? insuranceDoc.getInteger("amount", -1) : -1;
            }

            System.out.println("No match found for the insurance policy number.");
            return -1;
        } catch (Exception e) {
            System.err.println("An error occurred while retrieving the insurance amount: " + e.getMessage());
            return -1;
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }

    public void updatePatientInsuranceInfo(int patientId, String newInsuranceProviderName, String newInsurancePolicyNumber) {
        try {

            FindIterable<Document> result = patientCollection.find(new Document("id", patientId));


            MongoCursor<Document> cursor = result.iterator();
            if (cursor.hasNext()) {
                Document patientDoc = cursor.next();


                if (newInsuranceProviderName != null) {
                    patientDoc.put("insuranceProviderName", newInsuranceProviderName);
                }
                if (newInsurancePolicyNumber != null) {
                    patientDoc.put("insurancePolicyNumber", newInsurancePolicyNumber);
                }


                patientCollection.replaceOne(new Document("id", patientId), patientDoc);

                System.out.println("Patient insurance information updated successfully.");
            } else {
                System.out.println("No patient found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println("Error updating patient insurance information: " + e.getMessage());
        }
    }

    public void updateInsuranceInfo(String providerName, String newPolicyNumber) {
        try {

            FindIterable<Document> result = insuranceCollection.find(new Document("name", providerName));


            MongoCursor<Document> cursor = result.iterator();
            if (cursor.hasNext()) {
                Document insuranceDoc = cursor.next();


                if (newPolicyNumber != null) {
                    insuranceDoc.put("insurancePolicyNumber", newPolicyNumber);
                }


                insuranceCollection.replaceOne(new Document("name", providerName), insuranceDoc);

                System.out.println("Insurance information updated successfully.");
            } else {
                System.out.println("No insurance found with the given provider name.");
            }
        } catch (Exception e) {
            System.out.println("Error updating insurance information: " + e.getMessage());
        }
    }



    public void addEmailSubscriberToDatabase(Observer o) {


        if (o instanceof EmailSubscriber) {
            try {

                Document subscriberDocument = new Document("name", ((EmailSubscriber) o).getName())
                        .append("email", ((EmailSubscriber) o).getEmail())
                        .append("id",  ((EmailSubscriber) o).getId());


                emailSubscriberCollection.insertOne(subscriberDocument);

                System.out.println("Observer Added to database");


            } catch (Exception e) {
                System.err.println("DATABASE INSERTION ERROR: " + e.toString());
            }
        }
    }

//    public void printAllEmailSubscribers() {
//        try {
//            FindIterable<Document> subscribers = emailSubscriberCollection.find();
//
//            System.out.println("All Email Subscribers:");
//            for (Document subscriberDoc : subscribers) {
//                String name = subscriberDoc.getString("name");
//                String email = subscriberDoc.getString("email");
//
//                System.out.println("Name: " + name);
//                System.out.println("Email: " + email);
//                System.out.println("------------------------");
//            }
//
//        } catch (Exception e) {
//            System.err.println("DATABASE RETRIEVAL ERROR: " + e.toString());
//        }
//    }

//    public ArrayList<String> getAllEmailSubscribers() {
//        ArrayList<String> emailList = new ArrayList<>();
//
//        try {
//            FindIterable<Document> subscribers = emailSubscriberCollection.find();
//
//            for (Document subscriberDoc : subscribers) {
//                String email = subscriberDoc.getString("email");
//                emailList.add(email);
//            }
//
//        } catch (Exception e) {
//            System.err.println("DATABASE RETRIEVAL ERROR: " + e.toString());
//        }
//
//        return emailList;
//    }





    public List<Appointment> getAllAvailableAppointments() {
        List<Appointment> availableAppointments = new ArrayList<>();

        try {
            FindIterable<Document> result = appointmentCollection.find(new Document("Available", true));

            for (Document appointmentDoc : result) {

                int appointmentID = appointmentDoc.getInteger("AppointmentID");
                boolean available = appointmentDoc.getBoolean("Available");
                String date = appointmentDoc.getString("Date");
                String time = appointmentDoc.getString("Time");
                String day = appointmentDoc.getString("Day");
                String doctorName = appointmentDoc.getString("doctorName");

                Appointment appointment = new Appointment(appointmentID, available, date, time, day, doctorName);
                availableAppointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return availableAppointments;
    }


    public void updatePatientInfo(String name, String password, int choice, String valueToUpdate) {
        Scanner sc = new Scanner(System.in);

        try {

            FindIterable<Document> result = findPatientByNameAndPassword(name, password);

            MongoCursor<Document> cursor = result != null ? result.iterator() : null;
            if (cursor != null && cursor.hasNext()) {
                Document patientDoc = cursor.next();

                if (choice == 1 ) {

                    patientDoc.put("phoneNo", valueToUpdate);
                }

                if (choice == 2 ) {

                    patientDoc.put("email", valueToUpdate);
                }

                if (choice == 3){
                    patientDoc.put("insuranceProviderName", valueToUpdate);
                } if (choice == 4){
                    patientDoc.put("insurancePolicyNumber", valueToUpdate);

                }


                patientCollection.replaceOne(
                        new Document("name", name).append("password", password),
                        patientDoc
                );

                System.out.println("User information updated successfully.");
            } else {
                System.out.println("No user found with the given name and password.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removePatient(String name, String password) {


        FindIterable<Document> result = findPatientByNameAndPassword(name, password);

        MongoCursor<Document> cursor = result != null ? result.iterator() : null;
        if (cursor != null && cursor.hasNext()) {
            patientCollection.deleteOne(
                    new Document("name", name).append("password", password)
            );
            System.out.println("Patient removed successfully.");
            personCollection.deleteOne(
                    new Document("name", name).append("password", password)
            );
            System.out.println("Person removed successfully.");
        } else {
            System.out.println("No user found with the given name and password.");
        }

    }

    public List<String> getBookingsByUsernameAndPasswordRmi(String username, String password) throws RemoteException {
        List<String> bookedAppointments = new ArrayList<>();
        Patient patient = getPatientByNameAndPassword(username, password);

        if (patient != null) {
            int patientId = patient.getId();

            try {
                FindIterable<Document> result = bookingCollection.find(Filters.eq("Patient Id", patientId));

                for (Document bookingDoc : result) {
                    Document appointmentDoc = bookingDoc.get("appointment", Document.class);

                    if (appointmentDoc != null) {
                        Integer appointmentID = appointmentDoc.getInteger("AppointmentId");


                        if (appointmentID != null) {

                            String date = appointmentDoc.getString("Date");
                            String time = appointmentDoc.getString("Time");
                            String day = appointmentDoc.getString("Day");
                            String doctorName = appointmentDoc.getString("doctorName");

                            String bookingDetails = "AppointmentID: " + appointmentID + "\n" +
                                    "Date: " + date + "\n" +
                                    "Time: " + time + "\n" +
                                    "Day: " + day + "\n" +
                                    "Doctor Name: " + doctorName + "\n" +
                                    "\n-------------------------------------------------------------------------\n";

                            bookedAppointments.add(bookingDetails);
                        } else {
                            System.out.println("Warning: AppointmentID is null or not present in the appointment document.");
                        }
                    } else {
                        System.out.println("Warning: Appointment document is null or not present in the booking document.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Patient not found.");
        }

        return bookedAppointments;
    }



    public List<String> getAllAvailableAppointmentsRmi() {
        List<String> availableAppointments = new ArrayList<>();

        try {
            FindIterable<Document> result = appointmentCollection.find(new Document("Available", true));

            for (Document appointmentDoc : result) {
                int appointmentID = appointmentDoc.getInteger("AppointmentID");
                boolean available = appointmentDoc.getBoolean("Available");
                String date = appointmentDoc.getString("Date");
                String time = appointmentDoc.getString("Time");
                String day = appointmentDoc.getString("Day");
                String doctorName = appointmentDoc.getString("doctorName");

                String appointmentInfo = "AppointmentID: " + appointmentID + "\n" +
                        "Available: " + available + "\n" +
                        "Date: " + date + "\n" +
                        "Time: " + time + "\n" +
                        "Day: " + day + "\n" +
                        "Doctor Name: " + doctorName + "\n"+
                        "\n-------------------------------------------------------------------------\n";


                availableAppointments.add(appointmentInfo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return availableAppointments;
    }


//    public List<String> getAllPatientEmails() {
//        List<String> patientEmails = new ArrayList<>();
//
//        try {
//
//            FindIterable<Document> result = personCollection.find(new Document("personTypeID", 1));
//
//            for (Document personDoc : result) {
//
//                String email = personDoc.getString("Email");
//                if (email != null && !email.isEmpty()) {
//                    patientEmails.add(email);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        return patientEmails;
//
//    }



//    -----------------------------------------------------------------------------------------------------
//    Omar Mohammed
public Appointment getAppointmentById(int id) throws RemoteException {
    Appointment appointment = null;

    try {
        FindIterable<Document> result = findAppointmentById(id);

        try (MongoCursor<Document> cursor = result.iterator()) {
            if (cursor.hasNext()) {
                Document appointmentDoc = cursor.next();
                int appointmentID = appointmentDoc.getInteger("AppointmentID");
                boolean available = appointmentDoc.getBoolean("Available");
                String date = appointmentDoc.getString("Date");
                String time = appointmentDoc.getString("Time");
                String day = appointmentDoc.getString("Day");
                String doctorName = appointmentDoc.getString("doctorName");

                appointment = new Appointment(appointmentID, available, date, time, day, doctorName);
            } else {
                System.out.println("No appointment found with the given ID.");
            }
        }
    } catch (JsonSyntaxException | MongoException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return appointment;
}

    public void addAppointmentToDatabase(Appointment appointment) {
        try {
            Document lastAppointment = appointmentCollection.find().sort(new Document("AppointmentID", -1)).limit(1).first();
            int lastAppointmentId = (lastAppointment != null) ? lastAppointment.getInteger("AppointmentID", 0) : 0;
            int newAppointmentId = lastAppointmentId + 1;

            appointment.setAppointmentID(newAppointmentId);
            Document appointmentDoc = new Document("AppointmentID", newAppointmentId)
                    .append("Available", true)
                    .append("Date", appointment.getDate())
                    .append("Time", appointment.getTime())
                    .append("Day", appointment.getDay())
                    .append("doctorName", appointment.getDoctorName());

            appointmentCollection.insertOne(appointmentDoc);
            System.out.println("Appointment is added with ID: " + newAppointmentId);
        } catch (Exception e) {
            System.out.println("Error adding appointment to the database: " + e.getMessage());
        }
    }
    public boolean isDoctor(String name, String password) {


        Document doctor = personCollection.find(
                Filters.and(
                        Filters.eq("name", name),
                        Filters.eq("password", password),
                        Filters.eq("personTypeID", 2)
                )
        ).first();

        return doctor != null;
    }




    public boolean isAdmin(String name, String password) {


        Document admin = adminCollection.find(
                Filters.and(
                        Filters.eq("name", name),
                        Filters.eq("password", password),
                        Filters.eq("personTypeID", 3)
                )
        ).first();

        return admin != null;
    }

    public boolean isPatient(String name, String password) {


        Document patient = patientCollection.find(
                Filters.and(
                        Filters.eq("name", name),
                        Filters.eq("password", password),
                        Filters.eq("personTypeID", 1)
                )
        ).first();

        return patient != null;
    }


    public void insertDoctor(Doctor doctor) throws RemoteException {
        Document lastDoctor = personCollection.find().sort(new Document("id", -1)).limit(1).first();

        int lastDoctorId = (lastDoctor != null) ? lastDoctor.getInteger("id", 0) : 0;
        int newDoctorId = lastDoctorId + 1;

        doctor.setId(newDoctorId);
        doctor.setPersonTypeID(2);

        doctorCollection.insertOne(Document.parse(gson.toJson(doctor)));

        Person person = new Person(doctor.getName(), doctor.getPhoneNo(), doctor.getEmail(), doctor.getAge(), doctor.getPassword());
        person.setId(newDoctorId);
        person.setPersonTypeID(2);
        insertPerson(person);

        System.out.println("Doctor and corresponding Person are inserted with ID: " + newDoctorId + " and persontypeid: 2");
    }

    public int getPersonIdByName(String name) {
        Document personDocument = personCollection.find(Filters.eq("name", name)).first();
        if (personDocument != null) {
            return personDocument.getInteger("id", 0);
        } else {
            return 0;
        }
    }

    public int getPersonTypeId(String email, String password) {
        Document personDocument = personCollection.find(
                Filters.and(
                        Filters.eq("email", email),
                        Filters.eq("password", password)
                )
        ).first();

        if (personDocument != null) {
            return personDocument.getInteger("personTypeID", 0);
        } else {
            return 0;
        }
    }


    public void updateDoctor(String name, String newSpecialization) {
        UpdateResult result = doctorCollection.updateOne(
                Filters.eq("name", name),
                new Document("$set", new Document("specialization", newSpecialization)),
                new UpdateOptions().upsert(false)
        );

        System.out.println("Matched " + result.getMatchedCount() + " document(s) and modified " + result.getModifiedCount() + " document(s).");
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        Document lastRecord = MedicalRecordCollection.find().sort(new Document("MedicalRecordId", -1)).limit(1).first();

        int lastRecordId = (lastRecord != null) ? lastRecord.getInteger("MedicalRecordId", 0) : 0;
        int newRecordId = lastRecordId + 1;

        medicalRecord.setMedicalRecordId(newRecordId);

        MedicalRecordCollection.insertOne(Document.parse(gson.toJson(medicalRecord)));

        System.out.println("Medical Record is inserted with ID: " + newRecordId);
    }

    public void archiveMedicalRecord(String patientName) {
        Document medicalRecordDoc = MedicalRecordCollection.find(Filters.eq("PatientName", patientName)).first();

        if (medicalRecordDoc != null) {
            medicalRecordDoc.put("Completed", true);

            MedicalRecordCollection.deleteOne(Filters.eq("PatientName", patientName));

            ArchivedMedicalRecordCollection.insertOne(medicalRecordDoc);

            System.out.println("Medical Record for " + patientName + " has been archived.");
        } else {
            System.out.println("Medical Record not found for patient: " + patientName);
        }
    }


    public void updateMedicalRecord(String Pname, int updateOption, String updateValue) {
        Document medicalRecordDoc = MedicalRecordCollection.find(Filters.eq("PatientName", Pname)).first();

        if (medicalRecordDoc != null) {
            switch (updateOption) {
                case 1:
                    medicalRecordDoc.put("Description", updateValue);
                    break;
                case 2:
                    medicalRecordDoc.put("Diagnosis", updateValue);
                    break;
                default:
                    System.out.println("Invalid update option.");
                    return;
            }
            MedicalRecordCollection.replaceOne(Filters.eq("PatientName", Pname), medicalRecordDoc);

            System.out.println("Medical Record with ID " + Pname + " has been updated.");
        } else {
            System.out.println("Medical Record not found for ID: " + Pname);
        }
    }

    public List<String> getAllMedicalRecords() {
        List<String> medicalRecordsList = new ArrayList<>();
        FindIterable<Document> records = MedicalRecordCollection.find();

        for (Document record : records) {
            StringBuilder medicalRecordInfo = new StringBuilder();
            medicalRecordInfo.append("Medical Record ID: ").append(record.getInteger("MedicalRecordId")).append("\n");
            medicalRecordInfo.append("Patient ID: ").append(record.getInteger("PatientId")).append("\n");
            medicalRecordInfo.append("Patient Name: ").append(record.getString("PatientName")).append("\n");
            medicalRecordInfo.append("Description: ").append(record.getString("Description")).append("\n");
            medicalRecordInfo.append("Diagnosis: ").append(record.getString("Diagnosis")).append("\n");
            medicalRecordInfo.append("State: ").append(record.getBoolean("Completed")).append("\n");
            medicalRecordInfo.append("------------------------------");

            medicalRecordsList.add(medicalRecordInfo.toString());
        }

        return medicalRecordsList;
    }

    public List<String> getAllArchivedMedicalRecord() {
        List<String> archivedMedicalRecordsList = new ArrayList<>();
        FindIterable<Document> records = ArchivedMedicalRecordCollection.find();

        for (Document record : records) {
            StringBuilder archivedMedicalRecordInfo = new StringBuilder();
            archivedMedicalRecordInfo.append("Medical Record ID: ").append(record.getInteger("MedicalRecordId")).append("\n");
            archivedMedicalRecordInfo.append("Patient ID: ").append(record.getInteger("PatientId")).append("\n");
            archivedMedicalRecordInfo.append("Patient Name: ").append(record.getString("PatientName")).append("\n");
            archivedMedicalRecordInfo.append("Description: ").append(record.getString("Description")).append("\n");
            archivedMedicalRecordInfo.append("Diagnosis: ").append(record.getString("Diagnosis")).append("\n");
            archivedMedicalRecordInfo.append("State: ").append(record.getBoolean("Completed")).append("\n");
            archivedMedicalRecordInfo.append("------------------------------");

            archivedMedicalRecordsList.add(archivedMedicalRecordInfo.toString());
        }

        return archivedMedicalRecordsList;
    }

    public void addAmountToBalance(String email, int amountToAdd) {
        Document personDoc = patientCollection.find(new Document("email", email)).first();

        if (personDoc != null) {
            int currentBalance = personDoc.getInteger("balance", 0);
            int newBalance = currentBalance + amountToAdd;
            patientCollection.updateOne(new Document("email", email), new Document("$set", new Document("balance", newBalance)));
        } else {
            System.out.println("Person not found with email: " + email);
        }
    }

    public void subtractAmountToBalance(String email, int amountToSubtract) {
        Document personDoc = patientCollection.find(new Document("email", email)).first();

        if (personDoc != null) {
            int currentBalance = personDoc.getInteger("balance", 0);
            int newBalance = currentBalance - amountToSubtract;
            patientCollection.updateOne(new Document("email", email), new Document("$set", new Document("balance", newBalance)));
        } else {
            System.out.println("Person not found with email: " + email);
        }
    }

    public int getBalanceByEmail(String email) {
        Document personDoc = patientCollection.find(new Document("email", email)).first();
        if (personDoc != null) {
            return personDoc.getInteger("balance", 0);
        } else {
            System.out.println("Person not found with email: " + email);
            return -1;
        }
    }
    //Omar///
    public void addAppointment() throws RemoteException   {

        Appointment appointment = new Appointment();
        Scanner scadd = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name");
        String doctorName = sc.nextLine();
        System.out.println("Enter your Password");
        String doctorPassword = sc.nextLine();

        if (isDoctor(doctorName, doctorPassword)) {
            System.out.println("Enter your Date");
            String date = scadd.nextLine();
            System.out.println("Enter your Time");
            String time = scadd.nextLine();
            System.out.println("Enter your Day");
            String day = scadd.nextLine();
            appointment.setDate(date);
            appointment.setTime(time);
            appointment.setDay(day);
            appointment.setAvailable(true);
            appointment.setDoctorName(doctorName);


            Document lastAppointment = appointmentCollection.find().sort(new Document("AppointmentID", -1)).limit(1).first();

            int lastAppointmentId = (lastAppointment != null) ? lastAppointment.getInteger("AppointmentID", 0) : 0;
            int newAppointmentId = lastAppointmentId + 1;


            appointment.setAppointmentID(newAppointmentId);


            appointmentCollection.insertOne(Document.parse(DB.gson.toJson(appointment)));
            System.out.println("Appointment is added with ID: " + newAppointmentId);

        } else {
            System.out.println("Access denied. Only doctors can add appointments.");
        }
    }


    public void deleteAppointment(int appointmentId ) throws RemoteException  {


//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Enter your name");
//        String name = sc.nextLine();
//        System.out.println("Enter your password");
//        String password = sc.nextLine();
//
//        if (isDoctor(name, password)) {

        DeleteResult result = appointmentCollection.deleteOne(Filters.eq("AppointmentID", appointmentId));

        if (result.getDeletedCount() > 0) {
            System.out.println("Appointment with ID " + appointmentId + " is deleted.");
        } else {
            System.out.println("Appointment with ID " + appointmentId + " not found.");
        }
    }
//            else {
//            System.out.println("Access denied. Only doctors can delete appointments.");
//        }

    public boolean checkAppointmentById(int appointmentId) throws RemoteException {
        FindIterable<Document> result = appointmentCollection.find(eq("AppointmentID", appointmentId));
        MongoCursor<Document> cursor = result != null ? result.iterator() : null;

        return cursor != null && cursor.hasNext();
    }



    public Document updateAppointment(String naem , String password,int appointmentId, int choice, String updatedValue) throws RemoteException {
        // Check if the appointment exists with the given appointmentId
        boolean appointmentExists = checkAppointmentById(appointmentId);

        if (appointmentExists) {
            // Retrieve the appointment from the appointments collection
            Document existingAppointment = appointmentCollection.find(eq("AppointmentID", appointmentId)).first();

            if (existingAppointment != null) {
                System.out.println("Appointment FOUND before update:");
                System.out.println(existingAppointment);
                System.out.println("Updating field based on choice: " + choice);

                switch (choice) {
                    case 1:
                        existingAppointment.put("doctorName", updatedValue);
                        break;
                    case 2:
                        existingAppointment.put("Date", updatedValue);
                        break;
                    case 3:
                        existingAppointment.put("Time", updatedValue);
                        break;
                    case 4:
                        existingAppointment.put("Day", updatedValue);
                        break;
                    // Add more cases for other fields

                    default:
                        System.out.println("Invalid choice.");
                        return null; // or throw an exception if needed
                }

                // Update the appointment with the modified document
                UpdateResult updateResult = appointmentCollection.replaceOne(
                        eq("AppointmentID", appointmentId),
                        existingAppointment
                );

                System.out.println("Matched documents: " + updateResult.getMatchedCount());
                System.out.println("Modified documents: " + updateResult.getModifiedCount());

                System.out.println("Appointment information updated successfully.");


                Document updatedAppointment = appointmentCollection.find(eq("AppointmentID", appointmentId)).first();
                System.out.println("Updated Appointment:");
                System.out.println(updatedAppointment);
                return updatedAppointment;
            } else {
                System.out.println("No appointment found with the given appointmentId.");
            }
        } else {
            System.out.println("No appointment found with the given appointmentId.");
        }
        return null;
    }







    public List<String> viewAppointment(String doctorName) throws RemoteException {

        FindIterable<Document> records = appointmentCollection.find(eq("doctorName", doctorName));

        List<String> appointmentDetailsList = new ArrayList<>();


        for (Document record : records) {
            String appointmentDetails = "Appointment ID: " + record.getInteger("AppointmentID") +
                    "\nAvailability: " + record.getBoolean("Available") +
                    "\nDate:" + record.getString("Date") +
                    "\nTime: " + record.getString("Time") +
                    "\nDay: " + record.getString("Day") +
                    "\nDoctor Name: " + record.getString("doctorName") +
                    "\n------------------------------";

            appointmentDetailsList.add(appointmentDetails);
        }

        if (appointmentDetailsList.isEmpty()) {
            System.out.println("No appointments found for the specified doctor.");
        }

        return appointmentDetailsList;
    }
    public boolean checkDoctor(String name, String password) {

        FindIterable<Document> result = findDoctorByNameAndPassword(name, password);

        MongoCursor<Document> cursor = result.iterator();
        if (cursor.hasNext()) {
            Document doctorDoc = cursor.next();
            return true;
        } else {
            System.out.println("No user found with the given name and password.");
            return false;
        }

    }



    private FindIterable<Document> findDoctorByNameAndPassword(String name, String password) {
        try {
            return doctorCollection.find(
                    new Document("name", name)
                            .append("password", password)
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void addFeedbackToDatabase(feedback feedback) {
        try {
            Document lastFeedback = feedbackCollection.find().sort(new Document("feedbackId", -1)).limit(1).first();
            int lastFeedbackId = (lastFeedback != null) ? lastFeedback.getInteger("feedbackId", 0) : 0;
            int newFeedbackId = lastFeedbackId + 1;

            feedback.setFeedbackId(newFeedbackId);
            Document feedbackDoc = new Document("feedbackId", newFeedbackId)
                    .append("doctorName", feedback.getDoctorName())
                    .append("feedbackRate", feedback.getFeedbackRate())
                    .append("description", feedback.getDescription())
                    .append("feedbackStatus", feedback.getFeedbackStatues());


            feedbackCollection.insertOne(feedbackDoc);
            System.out.println("Feedback is added with ID: " + newFeedbackId);
        } catch (Exception e) {
            System.out.println("Error adding Feedback to the database: " + e.getMessage());
        }
    }

    public List<String> showFeedback() {

        List<String> feedbackList = new ArrayList<>();


        try (MongoCursor<Document> cursor = feedbackCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();

                String feedbackInfo = "Feedback ID: " + document.getInteger("feedbackId") +
                        "\nDoctor Name: " + document.getString("doctorName") +
                        "\nFeedback Rate: " + document.getInteger("feedbackRate") +
                        "\nDescription: " + document.getString("description") +
                        "\nFeedback Status: " + document.getString("feedbackStatus") +
                        "\n-----------------------------";
                feedbackList.add(feedbackInfo);
            }
        }catch (Exception e) {

            System.err.println("Error retrieving feedback details: " + e.getMessage());
        }
        System.out.println("Retrieved feedback details: " + feedbackList);
        return feedbackList;
    }

}
