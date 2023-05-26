package org.example;

import presentation.View;

import java.sql.SQLException;
/**
 * The main class of the application.
 */
public class Main {
    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args the command-line arguments
     * @throws SQLException if a database access error occurs
     */
    public static void main(String[] args) throws SQLException {
        // Create an instance of the View class
        View paginainitiala = new View(null);
    }
}
