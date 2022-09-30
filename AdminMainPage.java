package Bank;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class AdminMainPage extends AdminLogin {
    public void adminPage() {
        while (true) {
            System.out.println("================================>> ADMIN PAGE  <<================================");
            System.out.println("================================>> LOGIN PAGE  <<================================");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the admin name:");
            String adminName = scanner.nextLine();
            if (adminName.equals(getAdminName())) {
                System.out.println("Enter the password:");
                String adminPassword = scanner.nextLine();
                if (adminPassword.equals(getAdminPassword())) {
                    new AdminPage().adminPage();
                } else {
                    System.out.println("Incorrect Password");
                }
            } else {
                System.out.println("Admin name is wrong...");
            }
        }
    }
}
