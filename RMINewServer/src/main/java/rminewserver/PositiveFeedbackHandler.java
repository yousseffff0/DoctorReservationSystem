/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rminewserver;
import rmi.FeedbackHandler;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class PositiveFeedbackHandler implements FeedbackHandler {

    @Override
    public List<String> FeedbackHandlerr() throws RemoteException {
        List<String> feedbackList = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the "DoctorReservationSystem" database
            MongoDatabase database = mongoClient.getDatabase("DoctorReservationSystem");

            // Get the "feedback" collection
            MongoCollection<Document> collection = database.getCollection("feedback");

            // Create a query to find documents where feedbackrate is between 7 and 10
            Document query = new Document("feedbackRate", new Document("$gte", 7).append("$lte", 10));

            // Execute the query
            FindIterable<Document> result = collection.find(query);

            // Iterate over the result set
            try (MongoCursor<Document> cursor = result.iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();

                    int feedbackId = document.getInteger("feedbackId");
                    String doctorName = document.getString("doctorName");
                    int feedbackRate = document.getInteger("feedbackRate");
                    String description = document.getString("description");
                    String feedbackStatus = document.getString("feedbackStatus");

                    // Format feedback information and add it to the list
                    String feedbackInfo = "Feedback ID: " + feedbackId +
                            "\nDoctor Name: " + doctorName +
                            "\nFeedback Rate: " + feedbackRate +
                            "\nDescription: " + description +
                            "\nFeedback Status: " + feedbackStatus +
                            "\n--------------------------------";
                    feedbackList.add(feedbackInfo);
                }
            }
        } catch (MongoException e) {
            System.out.println("MongoDB Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return feedbackList;
    }
}
