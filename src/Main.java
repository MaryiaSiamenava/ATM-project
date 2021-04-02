import atm.ATM;
import money.Account;
import users.Admin;
import users.Person;
import users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final long SECRET_OPERATION = 7762323;

    public static void main(String[] args) {
        ATM atm = new ATM();
        Map<Long, Person> personVsIds = new HashMap<>();

        User curUser = new User("Александр", "Александров", "1234");
        Account userAccount = new Account();
        curUser.setAccount(userAccount);
        System.out.println(curUser.getID());
        personVsIds.put(curUser.getID(), curUser);

        System.out.println(curUser.getFirstName() + " " + curUser.getLastName());

        Admin curAdmin = new Admin("Николай", "Николаев", "4321");
        System.out.println(curAdmin.getID());
        personVsIds.put(curAdmin.getID(), curAdmin);
        System.out.println(curAdmin.getFirstName() + " " + curAdmin.getLastName());

        Scanner scan = new Scanner(System.in);

        boolean isNotSignedIn = true;
        while (isNotSignedIn) {
            System.out.println("Введите ID пользователя: ");
            long ID = scan.nextLong();

            if (personVsIds.containsKey(ID)) {
                final Person currentPerson = personVsIds.get(ID);

                System.out.println("Введите ПИН-код:");
                for (int i = 3; i > 0; i--) {
                    String pin = scan.next();
                    if (currentPerson.validateAccessCode(pin)) {
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
        isNotSignedIn = true;
        while (isNotSignedIn) {
            System.out.println("Выберите операцию:\n1 - пополнить счет.\n2 - снять деньги со счета: ");
            int userChoice = scan.nextInt();
            if (userChoice == 1) {
                System.out.println("Введите сумму, на которую желаете пополнить счет.");
                double amount = scan.nextDouble();
                userAccount.addMoneyToAccount(amount);
                System.out.println("Ваш счет пополнен на " + amount + " рублей. Текущий баланс равен " + userAccount.getBalance());
                System.out.println("Желаете совершить другую операцию?\n1 - да\n2 - нет");
                int kepOrQuit = scan.nextInt();
                if (kepOrQuit == 1) {
                    //TODO make implementation
                } else {
                    isNotSignedIn = false;
                    break;
                }
            } else if (userChoice == 2) {
                System.out.println("Введите сумму, которую желаете снять.");
                double amount = scan.nextDouble();
                if (Double.compare(userAccount.getBalance(), amount) > 0) {
                    curUser.takeMoneyFromATM(atm, amount);
                    userAccount.takeMoneyFromAccount(amount);
                    System.out.println("Операция произведена успешно. Остаток на счете равен " + userAccount.getBalance());
                } else {
                    System.out.println("На счете недостаточно денег. Текущий баланс равен " + userAccount.getBalance());
                }
            } else if (userChoice == SECRET_OPERATION) {
                System.out.println("Здравствуйте администратор.\nВведите 1 если желаете попожить деньги в банкомат.\nВведите 2 если желаете извлечь деньги из банкомата.");
                int adminChoice = scan.nextInt();
                if (adminChoice == 1) {
                    System.out.println("Какую сумму желаете внести?");
                    double amount = scan.nextDouble();
                    atm.putCashAmount(amount);
                    System.out.println("Операция произведена успешно. Текущий запас наличных средств: " + atm.getBalance());
                } else if (adminChoice == 2) {
                    System.out.println("Какую сумму желаете извлечь?");
                    double amount = scan.nextDouble();
                    atm.putCashAmount(amount);
                    System.out.println("Операция произведена успешно. Текущий запас наличных средств: " + atm.getBalance());
                }
            }
        }
    }
}
