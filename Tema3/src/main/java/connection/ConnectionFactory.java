package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The ConnectionFactory class provides a singleton connection to a PostgreSQL database.
 * It uses JDBC (Java Database Connectivity) to establish and manage the database connection.
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DBURL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "tudorioana1974";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    private ConnectionFactory(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL,USER,PASS);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }

}