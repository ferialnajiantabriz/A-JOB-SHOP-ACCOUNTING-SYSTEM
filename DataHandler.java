package finalproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataHandler {

    private Connection conn;

    // Azure SQL connection credentials
    private String server = "naji0002-sql-server.database.windows.net";
    private String database = "cs-dsa-4513-sql-db";
    private String username = "naji0002";
    private String password = "Scareface2010";

    // Resulting connection string
    final private String url =
            
String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                    server, database, username, password);

    // Initialize and save the database connection
    private void getDBConnection() throws SQLException {
        if (conn != null) {
            return;
        }

        this.conn = DriverManager.getConnection(url);
    }

    // Return the result of selecting everything from the movie_night 
table 
    public ResultSet getAllCustomer() throws SQLException {
        getDBConnection();
        
        final String sqlQuery = "SELECT * FROM Customer;";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        return stmt.executeQuery();
    }

    // Inserts a record into the movie_night table with the given 
attribute values
    public boolean addcustomer(
            String cname, String caddress, String category) throws 
SQLException {

        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery =
                "INSERT INTO customer " + 
                    "(cname, caddress, category) " + 
                "VALUES " + 
                "(?, ?, ?) ";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute 
values
//        stmt.setString(1, startTime);
//        stmt.setString(2, movieName);
//        stmt.setInt(3, duration);
//        stmt.setString(4, g1);
//        stmt.setString(5, g2);
//        stmt.setString(6, g3);
//        stmt.setString(7, g4);
//        stmt.setString(8, g5);
        stmt.setString(1, cname);
        stmt.setString(2, caddress);
        stmt.setString(3, category);
        
        // Execute the query, if only one record is updated, then we 
indicate success by returning true
        return stmt.executeUpdate() == 1;
    }
}
