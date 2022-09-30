package Bank;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAccountPage {
    public void userAccount() {
        while (true) {
            System.out.println("================================>> USER ACCOUNT <<================================");
            System.out.println("1) DEPOSIT");
            System.out.println("2) WITHDRAW");
            System.out.println("3) MONEY TRANSFER");
            System.out.println("4) ACCOUNT DETAILS");
            System.out.println("5) TRANSACTION DETAILS");
            System.out.println("6) EXIT");
            try {
                Scanner scanner = new Scanner(System.in);
                int number = scanner.nextInt();
                if (number == 1) {
                    new Deposit().deposit();
                } else if (number == 2) {
                    new Withdraw().withdraw();
                } else if (number == 3) {
                    new MoneyTransfer().moneyTransfer();
                } else if (number == 4) {
                    new AccountDetails().accountDetails();
                } else if (number == 5) {
                    new TransactionDetails().transaction();
                } else {
                    new BankMain().mainPage();
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter number...");
            }
        }
    }
}
