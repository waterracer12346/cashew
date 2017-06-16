/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympicsnetbeansproject;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

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
     *
     * @param f
     * @param i
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static void register(File f, int i) throws FileNotFoundException, SQLException {
        Scanner scan = new Scanner(f);
        Connection con = connectDB();
        Statement stmt = null;
        ResultSet rs;
        scan.useDelimiter(",");
        while (scan.hasNext()) {
            String username = scan.next();
            String password = scan.next();
            String firstName = scan.next();
            String lastName = scan.next();
            String type = scan.next();
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO users (id) VALUES \"" + username + "\"");
            stmt.executeUpdate("INSERT INTO users (password) VALUES \"" + password + "\"");
            stmt.executeUpdate("INSERT INTO users (firstname) VALUES \"" + firstName + "\"");
            stmt.executeUpdate("INSERT INTO users (lastname) VALUES \"" + lastName + "\"");
            stmt.executeUpdate("INSERT INTO users (type) VALUES \"" + type + "\"");
        }
    }

    /**
     * This will check the username and the password in the database to check
     * for the login
     *
     * @param username String, the student or teacher's username
     * @param password The password of the student of teacher's account
     * @return the first and last name of the person accessing the program, if
     * password is wrong then it will return wrong password
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
                    String thing = rs.getObject(3) + " " + rs.getObject(4);
                    return thing;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "Wrong Username/Password";
    }

    /**
     * Check the accessibility of the person that is logging in based on their
     * user type
     *
     * @param username the id in the database that the student goes by
     * @return this returns the type that the person is... 0 is student, 1 is
     * teacher, 2 is nothing
     */
    public static int checkType(String username) {
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

    public static void question(String username, String question, String priority, boolean active, LocalDateTime ldt) throws SQLException {
        Connection con = connectDB();
        Statement stmt;
        ResultSet rs;
        active = true;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE id = \"" + username + "\"");
            username = rs.getString(3);
            stmt.executeUpdate("INSERT INTO questions (question) VALUES \"" + question + "\"");
            stmt.executeUpdate("INSERT INTO questions (id) VALUES \"" + username + "\"");
            stmt.executeUpdate("INSERT INTO questions (priority) VALUES \"" + priority + "\"");
            stmt.executeUpdate("INSERT INTO questions (active) VALUES \"" + active + "\"");
            stmt.executeUpdate("INSERT INTO quesitons (id) VALUES \"" + rs.getCursorName() + "\"");
            stmt.executeUpdate("INSERT INTO questions (timestamp) VALUES \"" + LocalDateTime.now() + "\"");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void done(boolean active, String id) throws SQLException{
        active = false;
        Connection con = connectDB();
        Statement stmt;
        ResultSet rs;
        try{
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO questions (active) WHERE id = \"" + id + "\" VALUES \"" + active + "\"");
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection c = connectDB();

    }
}
