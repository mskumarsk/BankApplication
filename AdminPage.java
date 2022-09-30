package Bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPage {
    public void adminPage() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1) VIEW ACCOUNTS");
                System.out.println("2) ACCOUNT BLOCK");
                System.out.println("3) EXIT");
                System.out.println("Enter the number:");
                int number = scanner.nextInt();
                if (number == 1) {
                    new ViewAccount().view();
                } else if (number == 2) {
                    new AccountBlock().accountBlock();
                } else if (number == 3) {
                    BankMain bankMain = new BankMain();
                    bankMain.mainPage();
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter number...");
            }
        }
    }
}
