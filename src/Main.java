import ATM.ATM;
import Money.Account;
import Users.Admin;
import Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        ArrayList<Long> IDList = new ArrayList<Long>();

        User curUser = new User ("Александр", "Александров", "1234");
        Account userAccount = new Account();
        curUser.setAccount(userAccount);
        System.out.println(curUser.getID());
        IDList.add(curUser.getID());

        Admin curAdmin = new Admin("Николай", "Николаев", "123456789");
        System.out.println(curAdmin.getID());
        IDList.add(curAdmin.getID());

        Scanner scan = new Scanner(System.in);

        boolean isNotSignedIn = true;
        while (isNotSignedIn) {
            System.out.println("Введите ID пользователя.");
            long userID = scan.nextLong();

            if (IDList.contains(userID)) {
                System.out.println("Введите ПИН-код");
                for (int i = 3; i > 0; i--) {
                    String pin = scan.next();
                    if(curUser.validateAccessCode(pin)) {
                        System.out.println("Идентификация произведена успешно.");
                        isNotSignedIn = false;
                        break;
                    } else {
                        System.out.println("Неверный ПИН-код. Оставшееся количество попыток: " + (i - 1));
                    }
                }

            } else {
                System.out.println("Пользователя с таким ID не существует.");
            }
        }
    }
}
