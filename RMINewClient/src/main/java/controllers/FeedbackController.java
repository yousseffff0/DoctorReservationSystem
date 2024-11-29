package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rminewclient.FeedbackGUI;
import rminewclient.NegativeFeedbacks;
import rminewclient.NeutralFeedbacks;
import rminewclient.PositiveFeedbacks;

import rminewclient.ShowAllFeedbacks;


public class FeedbackController {
    private FeedbackGUI feedbackGUI;

    public FeedbackController(FeedbackGUI feedbackGUI) {
        this.feedbackGUI = feedbackGUI;
        attachEvents();
    }

    private void attachEvents() {
        feedbackGUI.getAllButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                feedbackGUI.dispose();

                
                ShowAllFeedbacks ShowAllFeedbacks = new ShowAllFeedbacks();
                new ShowFeedbackController(ShowAllFeedbacks);
            }
        });

        feedbackGUI.getPositiveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                feedbackGUI.dispose();

                
                PositiveFeedbacks PositiveFeedbacks = new PositiveFeedbacks();
                new PositiveFeedbackController(PositiveFeedbacks);
            }
        });
//
        feedbackGUI.getNeutralButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                feedbackGUI.dispose();

                
                NeutralFeedbacks updateMedicalRecordGUI = new NeutralFeedbacks();
                new NeutralFeedbackController(updateMedicalRecordGUI);

            }
        });

        feedbackGUI.getNegativeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                feedbackGUI.dispose();

                
                NegativeFeedbacks GetAllMedicalRecordsGUI = new NegativeFeedbacks();
                new NegativeFeedbackController(GetAllMedicalRecordsGUI);
            }
        });

        
    }
}