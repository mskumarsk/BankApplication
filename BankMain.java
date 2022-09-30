package Bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        BankMain bankMain = new BankMain();
        bankMain.mainPage();
    }
    public void mainPage() {
        while (true) {
            System.out.println("================================>> WELCOME  <<================================");
            System.out.println("1) ADMIN");
            System.out.println("2) USER");
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the number:");
                int number = scanner.nextInt();
                if (number == 1) {
                    new AdminMainPage().adminPage();
                } else if(number==2){
                    new UserMainPage().userPage();
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter number...");
            }
        }
    }
}
