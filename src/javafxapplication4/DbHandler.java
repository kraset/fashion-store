/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication4;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anc
 */
public class DbHandler {
    
    Connection dbConnection = null;
    
    public boolean connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
           // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
            String URL = "jdbc:mysql://127.0.0.1:3306/fashionstore?user=root&password=christian";
            Connection dbConnection = DriverManager.getConnection(URL);
            return true;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return false;
        }
    }//connect
    
    public void disconnect(){
        if (dbConnection!=null){
            try {
                dbConnection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList();
        if (dbConnection!=null){
            try {
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Employees");
                while(rs.next()){
                    int salary = rs.getInt("Salary");
                    String name = rs.getString("Name");
                    System.out.println("Customer Name: " + name + ", Salary " + salary);
                    employees.add(new Employee(name, salary));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return employees;
    }
}
