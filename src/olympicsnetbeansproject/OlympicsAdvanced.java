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
public class OlympicsAdvanced {

    public static Connection connectDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://nhs.cheshire.ca/olympics", "ics4u", "ics4urocks");

        } catch (SQLException e) {
            System.out.println("connection failed\n" + e);
            return null;
        }
        return connection;
    }

    public static ResultSet execute(Connection c, String statement) throws SQLException{
        Statement stmt = c.createStatement();
        ResultSet rs;
            rs = stmt.executeQuery(statement);
            return rs;
    }
    
    public static void display(ResultSet rs) throws SQLException{
        int cols = rs.getMetaData().getColumnCount();  
        while (rs.next()) {
                for (int i=1;i<=cols;i++)
              System.out.print(rs.getObject(i) + " ");
                System.out.println();
            }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello");
        Connection c = connectDB();
        if (c == null) System.exit(-1);
        Statement stmt;
        ResultSet rs;
        try {
            //get list of countries that participated in either olympics
            rs = execute(c,"SELECT Countries.name FROM Players, Countries where Players.country_id = Countries.country_id group by Countries.country_id");
display(rs);
           //get list of players in the 2004 Olympics
//get list of countries that participated in either olympics
            rs = execute(c,"SELECT Players.name from Players,Results,Olympics, Events where Players.player_id = Results.player_id AND Results.event_id = Events.event_id and Events.olympic_id = Olympics.olympic_id and Olympics.year=2004 group by Players.player_id");
display(rs);


        } catch (SQLException ex) {
System.out.println(ex.getMessage());

        }

    }
}
