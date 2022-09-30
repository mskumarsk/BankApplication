package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    Connection connection;

    public Connection database() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/BankDetails";
            String user = "root";
            String password = "8056812985msk";
            connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
