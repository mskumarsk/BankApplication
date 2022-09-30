package Bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AccountDetails {
    Connection connection;

    public void accountDetails() {
        System.out.println("===========================>>  ACCOUNT DETAILS  <<===========================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter your id:");
        String userId = scanner.nextLine();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            String getAccountDetails = "select user_name,date_of_birth,phone_no,gender,country,state," +
                    "aadhar from new_account where id='" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAccountDetails);
            if (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String phoneNo = resultSet.getString("phone_no");
                String gender = resultSet.getString("gender");
                String country = resultSet.getString("country");
                String state = resultSet.getString("state");
                String aadhar = resultSet.getString("aadhar");
                System.out.println("USER NAME           :  " + userName);
                System.out.println("DATE OF BIRTH       :  " + dateOfBirth);
                System.out.println("PHONE NO            :  " + phoneNo);
                System.out.println("GENDER              :  " + gender);
                System.out.println("COUNTRY             :  " + country);
                System.out.println("STATE               :  " + state);
                System.out.println("AADHAR NUMBER       :  " + aadhar);
            }
            String getDepositAmount = "select deposit from amount where user_id='" + userId + "'";
            Statement depositStatement = connection.createStatement();
            ResultSet depositResultSet = depositStatement.executeQuery(getDepositAmount);
            if (depositResultSet.next()) {
                String depositAmount = depositResultSet.getString("deposit");
                System.out.println("AMOUNT              :  " + depositAmount);
            }
        } catch (SQLException e) {
            System.out.println("Please check your name and account id...");
        }
    }
}
