package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_rental_db", "root", "1234");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database Driver not found!");
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (dbConnection==null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }

}
