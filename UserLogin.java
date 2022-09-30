package Bank;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserLogin {
    Connection connection;

    public void userLogin() {
        try {
            System.out.println("================================>> USER LOGIN <<================================");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name:");
            String userName = scanner.nextLine();
            System.out.println("Enter the password:");
            String userPassword = scanner.nextLine();
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            String getUserDetails = "select user_name,user_password from user_sign where user_name='" + userName + "' and " +
                    "user_password='" + userPassword + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUserDetails);
            if (resultSet.next()) {
                System.out.println("Login successfully...");
                new UserAccountPage().userAccount();
            } else {
                System.out.println("Please check your user name and password...");
                userLogin();
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your name and account id...");
        }

    }
}
