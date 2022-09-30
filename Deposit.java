package Bank;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Deposit {
    Connection connection;

    public void deposit() {
        System.out.println("================================>> DEPOSIT <<================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter your id:");
        String userId = scanner.nextLine();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            String getDepositAmount = "select * from new_account where user_name='" + name + "' and id='" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getDepositAmount);
            if (resultSet.next()) {
                try {
                    System.out.println("Enter deposit amount:");
                    String depositAmount = scanner.nextLine();
                    System.out.println("0  -> OK");
                    System.out.println("1  -> CANCEL");
                    System.out.println("Enter the number:");
                    int number = scanner.nextInt();
                    if (number == 0) {
                        String insertDepositAmount = "insert into deposit (user_id,user_name,deposit) values (?,?,?);";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertDepositAmount);
                        preparedStatement.setString(1, userId);
                        preparedStatement.setString(2, name);
                        preparedStatement.setString(3, depositAmount);
                        preparedStatement.executeUpdate();
                        System.out.println("Deposit successfully...");
                        String getUserAmount = "select * from amount where user_id='" + userId + "'";
                        ResultSet userAmountResultSet = statement.executeQuery(getUserAmount);
                        if (userAmountResultSet.next()) {
                            String userDepositAmount = userAmountResultSet.getString("deposit");
                            int amount = Integer.parseInt(userDepositAmount);
                            int totalAmount = amount + Integer.parseInt(depositAmount);
                            String updateDeposit = "update amount set deposit='" + totalAmount + "' where user_id='" + userId + "'";
                            PreparedStatement updateDepositStatement = connection.prepareStatement(updateDeposit);
                            updateDepositStatement.executeUpdate();
                        } else {
                            String insertAmount = "insert into amount (user_id,user_name,deposit) values (?,?,?);";
                            PreparedStatement insertAmountStatement = connection.prepareStatement(insertAmount);
                            insertAmountStatement.setString(1, userId);
                            insertAmountStatement.setString(2, name);
                            insertAmountStatement.setString(3, depositAmount);
                            insertAmountStatement.executeUpdate();
                        }
                    } else if (number == 1) {
                        System.out.println("Cancel...");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter number...");
                }
            } else {
                System.out.println("Please check your user name and password...");
            }
        } catch (SQLException e) {
            System.out.println("Please check your user name and password...");
        }
    }
}
