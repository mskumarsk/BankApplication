package Bank;

import java.net.PortUnreachableException;
import java.sql.PreparedStatement;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMainPage {
    public void userPage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("================================>> USER PAGE  <<================================");
                System.out.println("1) LOGIN");
                System.out.println("2) SIGN IN");
                System.out.println("3) NEW ACCOUNT");
                System.out.println("4) EXIT");
                System.out.println("Enter the number:");
                int number = scanner.nextInt();
                if (number == 1) {
                    new UserLogin().userLogin();
                } else if (number == 2) {
                    new SignIn().signIn();
                } else if (number == 3) {
                    new NewAccount().newAccount();
                } else if (number == 4) {
                    new BankMain().mainPage();
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter number...");
            }
        }
    }
}
