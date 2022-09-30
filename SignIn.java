package Bank;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignIn {
    Connection connection;

    public void signIn() {
        try {
            System.out.println("================================>> SIGN IN <<================================");
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.database();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name:");
            String userName = scanner.nextLine();
            System.out.println("Enter your mail id:");
            String userMail = scanner.nextLine();
            System.out.println("Enter the password:");
            String userPassword = scanner.nextLine();
            String insertDetails = "insert into user_sign(user_name,mail,user_password) values(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertDetails);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userMail);
            preparedStatement.setString(3, userPassword);
            preparedStatement.executeUpdate();
            System.out.println("Sing in successfully...");
            System.out.println("Do you want to new account?");
            System.out.println("1) YES");
            System.out.println("2) NO");
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the number:");
                int number = scan.nextInt();
                if (number == 1) {
                    new NewAccount().newAccount();
                } else if (number == 2) {
                    new UserMainPage().userPage();
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter number...");
                signIn();
            }
        } catch (SQLException e) {
            System.out.println("Please check your id and password...");
        }
    }
}
