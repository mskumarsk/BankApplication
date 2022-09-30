package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountBlock {
    Connection connection;

    public void accountBlock() {
        System.out.println("================================>> ACCOUNT BLOCK  <<================================");
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter customer account number:");
            int accountNumber = scanner.nextInt();
            String deleteAccount = "delete from new_account where id='" + accountNumber + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAccount);
            preparedStatement.executeUpdate();
            System.out.println("Account delete successfully...");
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your account number...");
        }
    }
}
