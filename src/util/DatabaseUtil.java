package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/tcg";
    private static String user;
    private static String pass;

    public static void initialize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        user = scanner.nextLine();
        System.out.println("Please enter your password: ");
        pass = scanner.nextLine();
    }

    public static Connection getConnection() throws SQLException {
        if (user == null || pass == null) {
            throw new IllegalStateException("Database connection failed");
        }
        return DriverManager.getConnection(URL, user, pass);
    }
}
