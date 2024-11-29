///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package rminewserver;
//
//import com.mongodb.MongoClient;
//import com.mongodb.client.model.Filters;
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.ArrayList;
//import org.bson.Document;
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//import com.google.gson.Gson;
//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.bson.Document;
///**
// *
// * @author meriam
// */
//public class Student {
//    private String name;
//    private int ID;
//    private String email;
//
//    DB db ;
//
//    public Student() throws RemoteException{
////         db=new DB();
////        db.mongoClient = new MongoClient();
////        db.database = db.mongoClient.getDatabase("School");
//    }
//
//    public Student(String name, int ID, String email) {
//        this.name = name;
//        this.ID = ID;
//        this.email = email;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
//
//}
