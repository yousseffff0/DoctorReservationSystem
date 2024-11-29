package rminewserver;

public interface Subject {

        public void addObserver(Observer o);
        public void removeObserver(Observer o);
        public void updateAll(String message, int patientId);
    }


