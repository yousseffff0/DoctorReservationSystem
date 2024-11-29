//import com.google.gson.Gson;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoClients;
//import de.flapdoodle.embed.mongo.MongodExecutable;
//import de.flapdoodle.embed.mongo.MongodProcess;
//import de.flapdoodle.embed.mongo.MongodStarter;
//import de.flapdoodle.embed.mongo.distribution.Version;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.Mockito;
//import rminewserver.Booking;
//import rminewserver.DB;
//import rminewserver.Patient;
//
//import java.rmi.RemoteException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//public class BookingTest {
//
//    private static MongoClient mongoClient;
//    private static MongoDatabase testDatabase;
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//
//        MongodExecutable mongodExecutable = starter.prepare(
//                new MongodConfig(Version.Main.PRODUCTION, 27017, false)
//        );
//        MongodProcess mongodProcess = mongodExecutable.start();
//
//        MongoClient client = new MongoClient("localhost", 27017);
//        testDatabase = client.getDatabase("DoctorReservationSystemTest");
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        mongoClient.close();
//    }
//
//    @Test
//    public void testCancelBooking() {
//        // Arrange
//        DB mockedDB = Mockito.mock(DB.class);
//        // Assuming you have a proper setup for mockedDB behavior, such as returning a patient, etc.
//        when(mockedDB.getPatientByNameAndPassword(anyString(), anyString())).thenReturn(new Patient());
//
//        // Assuming your Booking class constructor takes a MongoDatabase
//        Booking booking = new Booking(testDatabase);
//
//        // Act
//        try {
//            booking.cancelBooking("testName", "testPassword", 123);
//
//            // Assert
//            // Add assertions here based on the expected behavior after canceling a booking
//            verify(mockedDB).changeAppointmentState(123);
//            verify(mockedDB).removeBookingById(anyInt());
//            verify(mockedDB).getAppointmentById(123);
//            // Add more verifications as needed
//
//        } catch (RemoteException e) {
//            fail("Unexpected RemoteException");
//        }
//    }
//}
