package rminewserver;
import rmi.FeedbackHandler;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;

import java.util.ArrayList;

import rmi.*;

/**
 *
 * @author omar mohamed
 */
public class feedback extends UnicastRemoteObject implements feedbackInterface, FeedbackHandler{
    private String description;
    private int feedbackId;
    
    private String feedbackStatues;
    private int feedbackRate;
    private String doctorName;
   private FeedbackHandler feedbackHandler;
   DB db =new DB();

    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getFeedbackStatues() {
        return feedbackStatues;
    }

    public void setFeedbackStatues(String feedbackStatues) {
        this.feedbackStatues = feedbackStatues;
    }

   

    public int getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(int feedbackRate) {
        this.feedbackRate = feedbackRate;
    }
    


    public feedback(String description, int feedbackId,String doctorName,  String feedbackStatues,int feedbackRate)throws RemoteException {
        this.description = description;
        this.feedbackId = feedbackId;
        this.feedbackStatues = feedbackStatues;
        this.feedbackRate=feedbackRate;
        this.doctorName=doctorName;
        
        
    }
    public feedback()throws RemoteException{
         
    }
    public void setFeedbackHandler(FeedbackHandler FeedbackHandler) {
        this.feedbackHandler = FeedbackHandler;
    }
    @Override
    public List<String> FeedbackHandlerr() throws RemoteException{
    return feedbackHandler.FeedbackHandlerr();
    }
    
    @Override
   public void addFeedback(String doctorName, int feedbackRate, String description, String feedbackStatus) throws RemoteException {
    DB db = new DB();

    this.setDoctorName(doctorName);
    this.setFeedbackRate(feedbackRate);
    this.setDescription(description);
    this.setFeedbackStatues(feedbackStatus);
    

    db.addFeedbackToDatabase(this);
}
   
    @Override
   public List<String> showFeedback()throws RemoteException{
   return db.showFeedback();
   
   }
   
   
   
   
    

     @Override
     public List<String> feedbackCall(int choice) throws RemoteException {
    List<String> feedbackDetailsList = new ArrayList<>();

    switch (choice) {
        case 1:
            feedbackDetailsList = showFeedback();
            break;
        case 2:
            setFeedbackHandler(new PositiveFeedbackHandler());
            feedbackDetailsList = FeedbackHandlerr();
            break;
        case 3:
            setFeedbackHandler(new NeutralFeedbackHandler());
            feedbackDetailsList = FeedbackHandlerr();
            break;
        case 4:
            setFeedbackHandler(new NegativeFeedbackHandler());
            feedbackDetailsList = FeedbackHandlerr();
            break;
    }

    return feedbackDetailsList;
}
   
        
        
        
    }   
        


    
    


