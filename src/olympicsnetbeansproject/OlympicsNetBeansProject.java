/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympicsnetbeansproject;

import java.sql.*;

/**
 *
 * @author michael.roy-diclemen
 */
public class OlympicsNetBeansProject {

    public static Connection connectDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://10.242.72.7/cashew", "ics4u", "ics4urocks");
        } catch (SQLException e) {
            System.out.println("connection failed\n" + e);
            return null;
        }
        return connection;
    }

    /**
     *  This will check the username and the password in the database to check for the login
     * 
     * @param username String, the student or teacher's username
     * @param password The password of the student of teacher's account
     * @return the first and last name of the person accessing the app
     */
    public static String logIn(String username, String password) {
        Connection con = connectDB();
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE id = \"" + username + "\"");
            while (rs.next()) {
                if (password.equals(rs.getObject(2))) {
                    String thing = rs.getObject(3) + " " + rs.getObject(4) + " "+rs.getObject(5);
                    return thing;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    /**
     *  Check the accessibility of the person that is logging in based on their user type
     * 
     * @param username the id in the database that the student goes by
     * @return this returns the type that the person is... 0 is student, 1 is teacher
     */
    public static int checkType(String username){
        Connection con = connectDB();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE firstname = \"" + username + "\"");
            while (rs.next()) {
                return rs.getInt(5);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 2;
    }

    public static void question() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection c = connectDB();
    }
}
