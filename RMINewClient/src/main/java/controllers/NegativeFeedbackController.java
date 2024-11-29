package controller;

import rminewclient.ShowAllFeedbacks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import rmi.feedbackInterface;
import rmi.FeedbackHandler;
import rminewclient.NegativeFeedbacks;
import rminewclient.PositiveFeedbacks;

public class NegativeFeedbackController {
    private NegativeFeedbacks gui;
    private feedbackInterface feedbackInterface;

    public NegativeFeedbackController(NegativeFeedbacks gui) {
        this.gui = gui;
        initializeshowfeedbackInterface();
        attachEvents();
    }

    private void initializeshowfeedbackInterface() {
        try {
            Registry registry = LocateRegistry.getRegistry(1050);
            feedbackInterface = (feedbackInterface) registry.lookup("feedback");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            // Handle the exception based on your application's requirements
        }
    }

    private void attachEvents() {
        try {
            List<String> feedbacks = feedbackInterface.feedbackCall(4);

            // Update the JTextArea with the retrieved medical records
            updateRecordsTextArea(feedbacks);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            // Handle the RemoteException based on your application's requirements
        }

        gui.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the window when the "Close" button is clicked
                gui.dispose();
            }
        });
    }

    private void updateRecordsTextArea(List<String> feedbacks) {
        StringBuilder recordsText = new StringBuilder();

        for (String record : feedbacks) {
            recordsText.append(record).append("\n");
        }

        gui.getRecordsArea().setText(recordsText.toString());
    }
}