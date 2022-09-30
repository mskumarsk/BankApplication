package Bank;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NewAccount {
    Connection connection;

    public void newAccount() {
        System.out.println("================================>> NEW ACCOUNT <<================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String userName = scanner.nextLine();
        System.out.println("Enter your date of birth:");
        String userDateOfBirth = scanner.nextLine();
        System.out.println("Enter the phone number:");
        String userPhoneNumber = scanner.nextLine();
        System.out.println("Gender:");
        String userGender = scanner.nextLine();
        System.out.println("Enter country:");
        String userCountry = scanner.nextLine();
        System.out.println("Enter state:");
        String userState = scanner.nextLine();
        System.out.println("Enter your aadhar number:");
        String userAadharNumber = scanner.nextLine();
        try {
            System.out.println("0  -> OK");
            System.out.println("1  -> CANCEL");
            System.out.println("Enter the number:");
            int number = scanner.nextInt();
            if (number == 0) {
                try {
                    String insertUserDetails = "insert into new_account(user_name,date_of_birth,phone_no,gender,country,state," +
                            "aadhar) values(?,?,?,?,?,?,?);";
                    DatabaseConnection databaseConnection = new DatabaseConnection();
                    connection = databaseConnection.database();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertUserDetails);
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, userDateOfBirth);
                    preparedStatement.setString(3, userPhoneNumber);
                    preparedStatement.setString(4, userGender);
                    preparedStatement.setString(5, userCountry);
                    preparedStatement.setString(6, userState);
                    preparedStatement.setString(7, userAadharNumber);
                    preparedStatement.executeUpdate();
                    Scanner scan = new Scanner(System.in);
                    System.out.println("New account create successfully...");
                    System.out.println("Enter your name:");
                    String name = scan.nextLine();
                    System.out.println("Enter your phone number:");
                    String phoneNumber = scan.nextLine();
                    System.out.println("0  -> OK");
                    System.out.println("1  -> CANCEL");
                    System.out.println("Enter the number:");
                    int option = scanner.nextInt();
                    if (option == 0) {
                            System.out.println("Your account created...");
                            String getUserDetails = "select id,user_name,phone_no from new_account where " +
                                    "user_name='" + name + "' and phone_no='" + phoneNumber + "'";
                            PreparedStatement getUserDetailsStatement = connection.prepareStatement(getUserDetails);
                            ResultSet resultSet = getUserDetailsStatement.executeQuery();
                            if (resultSet.next()) {
                                System.out.println("YOUR NAME   :  " + resultSet.getString("user_name"));
                                System.out.println("YOUR ID     :  " + resultSet.getString("id"));
                                new UserAccountPage().userAccount();
                            } else {
                                System.out.println("Please check your name and phone number...");
                            }
                    } else if (option == 1) {
                        System.out.println("Cancel...");
                    }
                } catch (SQLException e) {
                    System.out.println("Please check your details...");
                }
            } else if (number == 1) {
                System.out.println("Cancel...");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter number...");
        }

    }
}
