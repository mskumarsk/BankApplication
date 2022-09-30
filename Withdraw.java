package Bank;

import java.sql.*;
import java.util.Scanner;

public class Withdraw {
    Connection connection;

    public void withdraw() {
        System.out.println("================================>> WITHDRAW <<================================");
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter user name:");
            String name = scanner.nextLine();
            System.out.println("Enter your id:");
            String userId = scanner.nextLine();
            String getWithdrawDetails = "select * from amount where user_id='" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWithdrawDetails);
            if (resultSet.next()) {
                System.out.println("YOUR DEPOSIT AMOUNT   :  " + resultSet.getString("deposit"));
                System.out.println("Enter withdraw amount:");
                String withdrawAmount = scanner.nextLine();
                String getDepositAmount = "select * from amount where user_id='" + userId + "'";
                ResultSet depositAmountResultSet = statement.executeQuery(getDepositAmount);
                if (depositAmountResultSet.next()) {
                    String deposit = depositAmountResultSet.getString("deposit");
                    if (Integer.parseInt(withdrawAmount) <= Integer.parseInt(deposit)) {
                        int amount = Integer.parseInt(deposit) - Integer.parseInt(withdrawAmount);
                        String updateDeposit = "update amount set deposit='" + amount + "' where user_id='" + userId + "'";
                        PreparedStatement updateDepositStatement = connection.prepareStatement(updateDeposit);
                        updateDepositStatement.executeUpdate();
                        String insertAmount = "insert into withdraw (user_id,user_name,withdraw) values (?,?,?);";
                        PreparedStatement insertAmountStatement = connection.prepareStatement(insertAmount);
                        insertAmountStatement.setString(1, userId);
                        insertAmountStatement.setString(2, name);
                        insertAmountStatement.setString(3, withdrawAmount);
                        insertAmountStatement.executeUpdate();
                        System.out.println("Withdraw successfully...");
                    }
                }
            } else {
                System.out.println("Please check your id...");
            }
        } catch (SQLException e) {
            System.out.println("Please check your name and account id...");
        }
    }
}
