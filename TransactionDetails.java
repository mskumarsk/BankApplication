package Bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionDetails {
    Connection connection;

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void transaction() {
        System.out.println("================================>> TRANSACTION DETAILS <<================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your id:");
        String userId = scanner.nextLine();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            String getDepositAmount = "select * from deposit where user_id='" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getDepositAmount);
            System.out.println("==================================================>>  DEPOSIT DETAILS  <<=========" +
                    "=======================================");
            System.out.print(" ======================================================================");
            System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                    "DEPOSIT") + " | " + centerString(20, "DATE") + " | ");
            System.out.print("\n ======================================================================");
            while (resultSet.next()) {
                String name = resultSet.getString("user_name");
                String deposit = resultSet.getString("deposit");
                String date = resultSet.getString("deposit_date");
                System.out.printf("\n | " + centerString(20, name) + " | " + centerString(20,
                        deposit) + " | " + centerString(20, date) + " | ");
            }
            System.out.print("\n ======================================================================");
            System.out.println();

            String getWithdrawDetails = "select * from withdraw where user_id='" + userId + "'";
            ResultSet withdrawResultSet = statement.executeQuery(getWithdrawDetails);
            System.out.println("==================================================>>  WITHDRAW DETAILS  <<=======" +
                    "=========================================");
            System.out.print(" ======================================================================");
            System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                    "WITHDRAW") + " | " + centerString(20, "DATE") + " | ");
            System.out.print("\n ======================================================================");
            while (withdrawResultSet.next()) {
                String name = withdrawResultSet.getString("user_name");
                String withdraw = withdrawResultSet.getString("withdraw");
                String date = withdrawResultSet.getString("withdraw_date");
                System.out.printf("\n | " + centerString(20, name) + " | " + centerString(20,
                        withdraw) + " | " + centerString(20, date) + " | ");
            }
            System.out.print("\n ======================================================================");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Please check your name and account id...");
        }
    }
}
