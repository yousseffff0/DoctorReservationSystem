/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rminewserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.sql.Connection;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author omar mohamed
 */
public class admin  extends Person{
   
    
   
  
    private static feedback feedback;
    

//    private static report report;
    
   

    public admin()throws RemoteException{

    };
    
     public admin(String name, String phoneNo, String email, String age, String password) throws RemoteException {
        super(name, phoneNo, email, age, password);
        
        
        //feedback = new feedback();
        
        
    }
    
     

    

    
}
