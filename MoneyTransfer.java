package Bank;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MoneyTransfer {
    Connection connection;

    public void moneyTransfer() {
        System.out.println("================================>> MONEY TRANSFER <<================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String userName = scanner.nextLine();
        System.out.println("Enter your id:");
        String userId = scanner.nextLine();
        System.out.println("Receiver account id:");
        String receiverId = scanner.nextLine();
        int amount = 0;
        String transferAmount = "";
        int deposit = 0;
        int senderDeposit = 0;
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            String getUserAmount = "select * from amount where user_id='" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUserAmount);
            if (resultSet.next()) {
                System.out.println("YOUR DEPOSIT AMOUNT   :  " + resultSet.getString("deposit"));
                String senderDepositAmount = resultSet.getString("deposit");
                senderDeposit = Integer.parseInt(senderDepositAmount);
            }
            System.out.println("Enter amount:");
            transferAmount = scanner.nextLine();
            String getReceiverAmount = "select * from amount where user_id='" + receiverId + "'";
            ResultSet receiverAmountResultSet = statement.executeQuery(getReceiverAmount);
            if (receiverAmountResultSet.next()) {
                deposit = Integer.parseInt(receiverAmountResultSet.getString("deposit"));
                amount = deposit + Integer.parseInt(transferAmount);
            }
            System.out.println("0  -> OK");
            System.out.println("1  -> CANCEL");
            System.out.println("Enter the number:");
            int number = scanner.nextInt();
            if (number == 0) {
                String updateAmount = "update amount set deposit='" + amount + "' where user_id='" + receiverId + "'";
                PreparedStatement updateAmountStatement = connection.prepareStatement(updateAmount);
                updateAmountStatement.executeUpdate();

                int senderAmount = senderDeposit - Integer.parseInt(transferAmount);
                String updateSenderAmount = "update amount set deposit='" + senderAmount + "' where user_id='" + userId + "'";
                PreparedStatement updateSenderAmountStatement = connection.prepareStatement(updateSenderAmount);
                updateSenderAmountStatement.executeUpdate();

                String insertAmount = "insert into withdraw (user_id,user_name,withdraw) values (?,?,?);";
                PreparedStatement insertAmountStatement = connection.prepareStatement(insertAmount);
                insertAmountStatement.setString(1, userId);
                insertAmountStatement.setString(2, userName);
                insertAmountStatement.setString(3, transferAmount);
                insertAmountStatement.executeUpdate();
                System.out.println("Transfer successfully...");
            } else if (number == 1) {
                System.out.println("Cancel...");
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please enter number...");
        }
    }
}
