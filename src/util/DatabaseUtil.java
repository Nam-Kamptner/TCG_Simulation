package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/tcg";

    public static Connection getConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter PostgreSQL username: ");
        String user = scanner.nextLine();

        System.out.println("Enter PostgreSQL password: ");
        String pass = scanner.nextLine();
        return DriverManager.getConnection(URL, user, pass);
    }
}
