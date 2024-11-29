import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import rminewserver.Booking;
import rminewserver.*;

import java.rmi.RemoteException;


public class DBTest {
    DB db = new DB();

    @Test
    public void DBTest() {
        assertTrue(db.checkPatient("youssef", "youssef123"));

    }


    @Test
    public void testBooking() throws RemoteException {
        DB db = new DB();
         Booking booking = new Booking();

          booking.bookAppointment("youssef", "youssef123", 1);

         assertFalse(db.isAppointmentAvailable(1));

    }

    @Test
    public void testPayment() throws RemoteException {
        DB db = new DB();
        CreditPaymentStrategy creditPayment = new CreditPaymentStrategy();

         int beforeAmount = db.getBalanceByEmail("ahmad@ayhaga.com");
        beforeAmount = beforeAmount -1000;

        creditPayment.MakePayment(1000, "ahmad@ayhaga.com");

         int afterAmount = db.getBalanceByEmail("ahmad@ayhaga.com");

         assertEquals(beforeAmount, afterAmount);
    }


}


