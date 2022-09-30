package Bank;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ViewAccount {
    Connection connection;

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void view() {
        System.out.println("================================>> VIEW ACCOUNTS <<================================");
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            String getAccountDetails = "select * from new_account";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAccountDetails);
            System.out.println("========================================================================" +
                    "=============>>  ACCOUNTS DETAILS  <<============================================" +
                    "============================");
            System.out.print(" =============================================================================" +
                    "==========================================================================================" +
                    "=============");
            System.out.printf("\n | " + centerString(15, "ID") + " | " + centerString(20,
                    "NAME") + " | " + centerString(20, "DATE OF BIRTH") + " | " + centerString(20,
                    "PHONE NO") + " | " + centerString(20, "GENDER") + " | " + centerString(20,
                    "COUNTRY") + " | " + centerString(20, "STATE") + " | " + centerString(20,
                    "AADHAR") + " | ");
            System.out.print("\n =========================================================================" +
                    "=====================================================================================" +
                    "======================");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String userName = resultSet.getString("user_name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String phoneNo = resultSet.getString("phone_no");
                String gender = resultSet.getString("gender");
                String country = resultSet.getString("country");
                String state = resultSet.getString("state");
                String aadhar = resultSet.getString("aadhar");
                System.out.printf("\n | " + centerString(15, id) + " | " + centerString(20,
                        userName) + " | " + centerString(20, dateOfBirth) + " | " + centerString(20,
                        phoneNo) + " | " + centerString(20, gender) + " | " + centerString(20,
                        country) + " | " + centerString(20, state) + " | " + centerString(20, aadhar) + " | ");
            }
            System.out.print("\n ==============================================================================" +
                    "==========================================================================================" +
                    "============");
            System.out.println();
            String getAmount= "select * from amount";
            ResultSet amountResultSet = statement.executeQuery(getAmount);
            System.out.println("==================================================>>  AMOUNT DETAILS  <<=======" +
                    "=========================================");
            System.out.print(" =================================================================");
            System.out.printf("\n | " + centerString(15, "ID") + " | " + centerString(20,
                    "NAME") + " | " + centerString(20, "AMOUNT") + " | ");
            System.out.print("\n =================================================================");
            while (amountResultSet.next()) {
                String id = amountResultSet.getString("user_id");
                String userName = amountResultSet.getString("user_name");
                String amount = amountResultSet.getString("deposit");
                System.out.printf("\n | " + centerString(15, id) + " | " + centerString(20,
                        userName) + " | " + centerString(20, amount) + " | ");
            }
            System.out.print("\n =================================================================");
            System.out.println();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your details...");
        }
    }
}
